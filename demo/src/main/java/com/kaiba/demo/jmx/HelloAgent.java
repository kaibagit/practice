//package com.kaiba.demo.jmx;
//
//import com.kaiba.demo.jmx.beans.ModelMBeanUtils;
//import com.sun.jdmk.comm.HtmlAdaptorServer;
//
//import javax.management.*;
//import javax.management.modelmbean.RequiredModelMBean;
//import java.lang.management.ManagementFactory;
//
///**
// * Created by luliru on 2017/3/8.
// */
//public class HelloAgent {
//    public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
//
//        //create mbean server
//        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//
//        //create object name
//        ObjectName helloName = new ObjectName("jmxMBean:name=hello");
//
//        //receive ModelMBean by ModelMBeanUtils
//        RequiredModelMBean hello = ModelMBeanUtils.createModelMBean();
//
//        //create mbean and register mbean
//        server.registerMBean(hello, helloName);
//
//        //create adaptor, adaptor is just a form as show mbean. It has no relation to specific business mbean.
//        HtmlAdaptorServer adaptor  = new HtmlAdaptorServer();
//        //create adaptor name
//        ObjectName adaptorName = new ObjectName("jmxAaptor:name=adaptor,port=5050");
//        //register adaptor and adaptor name
//        server.registerMBean(adaptor, adaptorName);
//
//        adaptor.setPort(9999);
//        adaptor.start();
//        System.out.println("....................server start....................");
//    }
//}
