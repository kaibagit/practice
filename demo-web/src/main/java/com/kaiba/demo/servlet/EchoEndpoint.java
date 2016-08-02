package com.kaiba.demo.servlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by luliru on 2016/8/2.
 */
@ServerEndpoint("/echo")
public class EchoEndpoint {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        //以下代码省略...
    }

    @OnMessage
    public String onMessage(String message) {
        //以下代码省略...
        return null;
    }

//    @Message(maxMessageSize=6)
//    public void receiveMessage(String s) {
//        //以下代码省略...
//    }

    @OnError
    public void onError(Throwable t) {
        //以下代码省略...
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        //以下代码省略...
    }

}
