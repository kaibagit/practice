package com.luliru.netty4.udp.multicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by luliru on 2019/2/20.
 */
public class MulticastClient{

    public static void main(String[] args) throws Exception {
        InetSocketAddress groupAddress = new InetSocketAddress("239.255.27.1", 1234);

        EventLoopGroup group = new NioEventLoopGroup();
        try {
//            NetworkInterface ni = NetworkInterface.getByName("en1");
            NetworkInterface ni = NetUtil.LOOPBACK_IF;
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            InetAddress localAddress = null;
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet4Address){
                    localAddress = address;
                }
            }

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channelFactory(new ChannelFactory<NioDatagramChannel>() {

                        public NioDatagramChannel newChannel() {
                            return new NioDatagramChannel(InternetProtocolFamily.IPv4);
                        }
                    })
                    .localAddress(localAddress, groupAddress.getPort())
                    .option(ChannelOption.IP_MULTICAST_IF, ni)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientMulticastHandler());
                        }
                    });

            Channel ch = b.bind().sync().channel();
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("QOTM?", CharsetUtil.UTF_8),
                    groupAddress)).sync();

            if (!ch.closeFuture().await(5000)) {
                System.err.println("QOTM request timed out.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}