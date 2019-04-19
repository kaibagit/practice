package com.luliru.practice.groovy.demo

import groovy.sql.Sql

/**
 * Created by luliru on 2018/9/13.
 */
class Jdbc {

    static void main(args) {
        def db_url="jdbc:mysql://localhost:3306/test"
        def username="root"
        def password=""
        def driverClass="com.mysql.jdbc.Driver"
        def sql = Sql.newInstance(db_url, username, password, driverClass)
        sql.execute("")
        (100000000..200000000).each {i ->
            sql.execute("insert into order_event values (${i},1,${i}, 10,${con}, ${path}, ${pan})")
        }
//        sql.eachRow("select * from dat"){row ->
//            println row.id
//            println row.path
//            def con ="un"
//            def path = "D://asd"
//            def pan = "E:"
//            sql.execute("insert into data_path (s_size, b_size, con,path,pan) values (1000, 10,${con}, ${path}, ${pan})")
//        }
    }
}
