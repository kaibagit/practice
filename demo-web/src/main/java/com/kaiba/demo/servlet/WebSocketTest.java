package com.kaiba.demo.servlet;

import java.io.IOException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketTest {

    @OnMessage
    public void onMessage(String message,final Session session)
            throws IOException, InterruptedException {

        // Print the client message for testing purposes
        System.out.println("Received: " + message);

        // Send the first message to the client
        session.getBasicRemote().sendText("This is the first server message");

        new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000L);
                    session.getBasicRemote().sendText("server says.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected. session:"+session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed. session:"+session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}
