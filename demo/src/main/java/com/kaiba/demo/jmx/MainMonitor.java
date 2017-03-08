package com.kaiba.demo.jmx;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.*;

import com.kaiba.demo.jmx.beans.ConfigMBean;
import com.kaiba.demo.jmx.beans.Config;
import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * Created by luliru on 2017/3/7.
 */
public class MainMonitor {

    public static void main(String[] args) throws Exception {
        //MBeanServer是MBean的容器，可以通过多种方式获得MBeanServer的实例
        //不能在jconsole中使用
        //MBeanServer mbserver = MBeanServerFactory.createMBeanServer();
        //可以在jconsole中使用
        MBeanServer mbserver = ManagementFactory.getPlatformMBeanServer();

        //创建MBean（注意UserActionMBean必须是public）
        ConfigMBean defaultConfig = new Config();

        //将MBean注册到MBeanServer中
        mbserver.registerMBean(defaultConfig, new ObjectName("ConfigMBean:name=defaultConfig"));

        //启用HTML协议适配器，使之能够通过浏览器访问MBean，地址为http://127.0.0.1:9999
        //注意HtmlAdaptorServer需要引入jmxtool.jar
        //Maven坐标为<dependency groupId="com.sun.jdmk" artifactId="jmxtools" version="1.2.1"/>
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.setPort(9999);
        mbserver.registerMBean(adapter, new ObjectName("MyAppMBean:name=htmlAdapter,port=9999"));
        adapter.start();

        //JMXConnectorServer service
        try {
            //这句话非常重要，不能缺少！注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
            LocateRegistry.createRegistry(8888);
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8888/server");
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbserver);
            System.out.println("....................begin rmi start.....");
            cs.start();
            System.out.println("....................rmi start.....");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.currentThread().join();
    }
}
