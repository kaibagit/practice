package com.kaiba.demo.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by luliru on 2019/2/27.
 */
public class Client extends WebSocketClient {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public Client(URI serverUri){
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("打开新的WebSocket连接:HttpStatus={},HttpStatusMessage={}",serverHandshake.getHttpStatus(),serverHandshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        log.debug("收到消息：{}",message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("WebSocket连接关闭:code={},reason={},remote={}",code,reason,remote);
    }

    @Override
    public void onError(Exception ex) {
        log.warn("WebSocket连接异常",ex);
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        Client client = new Client(new URI("ws://localhost:8081/clientws"));
        client.setTcpNoDelay(true);
        client.connectBlocking();
        client.send("hello");
        client.closeBlocking();
    }
}
