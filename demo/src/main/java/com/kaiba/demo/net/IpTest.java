package com.kaiba.demo.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by luliru on 2016/12/1.
 */
public class IpTest {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        try {
            Enumeration<NetworkInterface> interfaces= NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while(addresss.hasMoreElements())
                {
                    InetAddress nextElement = addresss.nextElement();
                    String hostAddress = nextElement.getHostAddress();
                    System.out.println("本机IP地址为：" +hostAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(InetAddress.getLocalHost().getHostAddress().toString());

    }
}
