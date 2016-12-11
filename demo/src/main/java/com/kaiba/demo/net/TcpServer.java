package com.kaiba.demo.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by luliru on 2016/12/10.
 */
public class TcpServer {

    public static void main(String[] args) throws IOException {
//        ServerSocket server = new ServerSocket(8888,50, InetAddress.getByName("192.168.56.1"));
//        ServerSocket server = new ServerSocket(8888,50, InetAddress.getByName("0.0.0.0"));
        ServerSocket server = new ServerSocket(8888);
        while(true){
            Socket socket = server.accept();	//接受socket连接，在连接传入之前一直阻塞
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("客户端："+in.readUTF());
            out.writeUTF("你好，客户端地址信息："+socket.getInetAddress()+"\t客户端通信端口号："+socket.getPort());
            out.close();
            socket.close();
        }
    }
}
