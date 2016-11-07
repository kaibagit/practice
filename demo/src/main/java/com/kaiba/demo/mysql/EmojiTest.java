package com.kaiba.demo.mysql;

import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * Created by luliru on 2016/11/5.
 */
public class EmojiTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String str = "\uD83D\uDC51\uD83D\uDD25";
        byte[] bytes = str.getBytes("utf-8");
        System.out.println(str);
        System.out.println(bytes.length);

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8","root","123456");
        PreparedStatement pstmt = conn.prepareStatement("insert into emoji(name) values(?)");
        pstmt.setString(1,str);
        pstmt.execute();

        pstmt = conn.prepareStatement("select name from emoji where id = ?");
        pstmt.setInt(1,1);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));
        }

        conn.close();
    }

}
