package com.kaiba.demo.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by luliru on 2016/12/10.
 */
public class TcpClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("hello");
        System.out.println("服务器："+in.readUTF());
        in.close();
        socket.close();
    }
}
