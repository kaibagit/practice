package com.kaiba.demo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by luliru on 2017/6/11.
 */
@WebServlet(name="Servlet3", urlPatterns={"/demo", "/servlet"},asyncSupported=true)//此为Servlet3新增的注解支持，asyncSupported=true表示支持异步
//也可在web.xml中添加<async-supported>true</async-supported>
public class Servlet3 extends HttpServlet{

    private static final Logger log = LoggerFactory.getLogger(Servlet3.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("下订单开始: " + new Date() + "<br/>");
        log.info("下订单开始");
        out.flush();

        AsyncContext ctx = req.startAsync();
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
        //异步去执行开通订单
        new Thread(new CheckOrder(ctx)).start();
        out.println("订购成功: " + new Date()+ "<br/>");
        log.info("订购成功");
        out.flush();
    }
}

class CheckOrder implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(CheckOrder.class);

    private AsyncContext ctx = null;

    public CheckOrder(AsyncContext ctx) {
        this.ctx = ctx;
    }
    public void run() {
        try {
            //模拟开通等待
            Thread.sleep(3000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("已经有权限了，let's go! : " + new Date() );
            log.info("已经有权限了");
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
