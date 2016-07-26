//package com.luliru.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
///**
// * Created by user on 2016/4/14.
// */
//public class NettyServer {
//    final static int port = 8080;
//
//    public static void main(String[] args) {
//        Server server = new Server();
//        server.config(port);
//        server.start();
//    }
//}
//
//class Server {
//    ServerBootstrap bootstrap;
//    Channel parentChannel;
//    InetSocketAddress localAddress;
//    MyChannelHandler channelHandler = new MyChannelHandler();
//
//    Server() {
//        bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
//                Executors.newCachedThreadPool(), Executors
//                .newCachedThreadPool()));
//        bootstrap.setOption("reuseAddress", true);
//        bootstrap.setOption("child.tcpNoDelay", true);
//        bootstrap.setOption("child.soLinger", 2);
//        bootstrap.getPipeline().addLast("servercnfactory", channelHandler);
//    }
//
//    void config(int port) {
//        this.localAddress = new InetSocketAddress(port);
//    }
//
//    void start() {
//        parentChannel = bootstrap.bind(localAddress);
//    }
//
//    class MyChannelHandler extends SimpleChannelHandler {
//
//        @Override
//        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
//                throws Exception {
//            System.out.println("Channel closed " + e);
//        }
//
//        @Override
//        public void channelConnected(ChannelHandlerContext ctx,
//                                     ChannelStateEvent e) throws Exception {
//            System.out.println("Channel connected " + e);
//        }
//
//        @Override
//        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
//                throws Exception {
//            try {
//                System.out.println("New message " + e.toString() + " from "
//                        + ctx.getChannel());
//                processMessage(e);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                throw ex;
//            }
//        }
//
//        private void processMessage(MessageEvent e) {
//            Channel ch = e.getChannel();
//            ch.write(e.getMessage());
//        }
//    }
//}
//
//
