package com.kaiba.demo.derby;

import java.sql.*;

/**
 * EmbeddedDemo
 * Created by luliru on 2019-06-06.
 */
public class EmbeddedDemo {

    public static void main(String[] args) throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();//加载驱动
        Connection conn = DriverManager.getConnection("jdbc:derby:testdb;create=true");//连接数据库
        Statement st = conn.createStatement();
        st.execute("create table USER_INFO (ID INT NOT NULL,NAME VARCHAR(10) NOT NULL)");//建表
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (1,'hermit')");//插入数据
        ResultSet rs = st.executeQuery("select * from USER_INFO");//读取刚插入的数据
        DriverManager.getConnection("jdbc:derby:;shutdown=true");//关闭数据库
    }
}
