package com.kaiba.demo.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by luliru on 2017/9/7.
 */
@WebServlet(name="FormServlet", urlPatterns={"/form"})
public class FormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(req.getInputStream(), baos);
        byte[] content = baos.toByteArray();
        System.out.println(new String(content));
    }
}
