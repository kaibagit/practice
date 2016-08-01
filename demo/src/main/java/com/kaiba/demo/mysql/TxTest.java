package com.kaiba.demo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by luliru on 2016/8/1.
 */
public class TxTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","123456");
        conn.setAutoCommit(false);
        String sql_one = "insert into db_0.t_order_0(order_id,user_id) values(10088,1)";
        String sql_two = "insert into db_1.t_order_0(order_id,user_id) values(10088,1)";
        Statement stmt = conn.createStatement();
        stmt.execute(sql_one);
        stmt.execute(sql_two);
        conn.rollback();
//        conn.commit();
        conn.close();
    }
}
