package com.kaiba.demo.datasource.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luliru on 2018/1/17.
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(200);
        druidDataSource.setMaxWait(100);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(3600000);
        druidDataSource.setValidationQuery("SELECT 'x'");
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        List<Filter> filters = new ArrayList();
        filters.add(new DruidFilter());
        druidDataSource.setProxyFilters(filters);

        druidDataSource.init();


        DruidPooledConnection conn = null;
        for(int i=0;i<10;i++){
            conn = druidDataSource.getConnection();
//            conn.close();
        }


        System.out.println(druidDataSource);
    }
}
