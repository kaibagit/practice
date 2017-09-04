package com.kaiba.demo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luliru on 2017/9/4.
 */
@WebServlet(name="AsyncIoServlet3", urlPatterns={"/asyncio"},asyncSupported=true)
public class AsyncIoServlet3 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AsyncIoServlet3.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext ctx = req.startAsync();
        ctx.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                //在这里处理正常结束的逻辑
            }
            public void onTimeout(AsyncEvent event) throws IOException {
                //在这里处理超时的逻辑
            }
            public void onError(AsyncEvent event) throws IOException {
                //在这里处理出错的逻辑
            }
            public void onStartAsync(AsyncEvent event) throws IOException {
                //在这里处理开始异步线程的逻辑
            }
        });
        //设置超时的时间，到了时间以后，会回调onTimeout的方法
        ctx.setTimeout(10000L);


        final ServletInputStream inputStream = req.getInputStream();
        final ServletOutputStream outputStream = resp.getOutputStream();
        inputStream.setReadListener(new ReadListener() {

            public void onDataAvailable() throws IOException {
                log.info("ReadListener.onDataAvailable");
                try {
                    StringBuilder sb = new StringBuilder();
                    int len = -1;
                    byte b[] = new byte[1024];
                    while (inputStream.isReady() && (len = inputStream.read(b)) != -1) {
                        String data = new String(b, 0, len);
                        log.info(data);
                    }
                } catch (IOException ex) {
                    log.error("",ex);
                }
            }

            public void onAllDataRead() throws IOException {

            }

            public void onError(Throwable t) {
                log.error("ReadListener.onError",t);
                ctx.complete();
            }
        });
        outputStream.setWriteListener(new WriteListener() {

            public void onWritePossible() throws IOException {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputStream.print("hello</br>");
                ctx.complete();
            }

            public void onError(Throwable t) {

            }
        });

    }
}
