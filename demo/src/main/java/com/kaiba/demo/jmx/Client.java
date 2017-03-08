package com.kaiba.demo.jmx;

import com.kaiba.demo.jmx.beans.ConfigMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by luliru on 2017/3/8.
 */
public class Client {

    public static void main(String[] args) throws Exception {

        //connect JMX
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8888/server");
        JMXConnector jmxc = JMXConnectorFactory.connect(url,null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName("ConfigMBean:name=defaultConfig");

        //print domains
        System.out.println("Domains:---------------");
        String domains[] = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("\tDomain[" + i +"] = " + domains[i]);
        }
        //MBean count
        System.out.println("MBean count = " + mbsc.getMBeanCount());

        //process attribute
        mbsc.setAttribute(mbeanName, new Attribute("Date", "20170304"));//set value
        System.out.println("Date = " + mbsc.getAttribute(mbeanName, "Date"));//get value

        //invoke via proxy
        ConfigMBean proxy = (ConfigMBean) MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, ConfigMBean.class, false);
        proxy.set("client");

        //invoke via rmi
        mbsc.invoke(mbeanName, "summary", null, null);
        mbsc.invoke(mbeanName, "set", new Object[] { "I'll connect to JMX Server via client2." }, new String[] { String.class.getName() });

        ClassLoadingMXBean classLoadingMXBean = MBeanServerInvocationHandler.newProxyInstance(mbsc, new ObjectName("java.lang:type=ClassLoading"), ClassLoadingMXBean.class, false);
        System.out.println("loadedClassCount:"+classLoadingMXBean.getLoadedClassCount());
        GarbageCollectorMXBean garbageCollectorMXBean = MBeanServerInvocationHandler.newProxyInstance(mbsc, new ObjectName("java.lang:type=GarbageCollector,name=PS MarkSweep"), GarbageCollectorMXBean.class, false);
        System.out.println("collectionTime:"+garbageCollectorMXBean.getCollectionTime());
        ThreadMXBean threadMXBean = MBeanServerInvocationHandler.newProxyInstance(mbsc, new ObjectName("java.lang:type=Threading"), ThreadMXBean.class, false);
        System.out.println("threadCount:"+threadMXBean.getThreadCount());

        //get mbean information
        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);
        System.out.println("Hello Class:" + info.getClassName());
        System.out.println("Hello Attriber:" + info.getAttributes()[0].getName());
        System.out.println("Hello Operation:" + info.getOperations()[0].getName());

        //ObjectName of MBean
        System.out.println("all ObjectName:---------------");
        Set set = mbsc.queryMBeans(null, null);
        for (Iterator it = set.iterator(); it.hasNext();) {
            ObjectInstance oi = (ObjectInstance)it.next();
            System.out.println("\t" + oi.getObjectName());
        }
        jmxc.close();
    }
}
