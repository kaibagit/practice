package com.kaiba.demo.mysql;

import java.sql.*;

/**
 * Created by luliru on 2016/11/5.
 */
public class EmojiTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String str = "☺❤☎✉✌☝";
        System.out.println(str);

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF8","root","123456");
        PreparedStatement pstmt = conn.prepareStatement("insert into emoji(name) values(?)");
        pstmt.setString(1,str);
        pstmt.execute();

        pstmt = conn.prepareStatement("select name from emoji where id = ?");
        pstmt.setInt(1,4);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));
        }

        conn.close();
    }

}
