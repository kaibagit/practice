package com.kaiba.demo.datasource.druid;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by luliru on 2018/1/17.
 */
public class DruidFilter extends FilterAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 从连接池中获取连接
     * @param chain
     * @param dataSource
     * @param maxWaitMillis
     * @return
     * @throws SQLException
     */
    @Override
    public DruidPooledConnection dataSource_getConnection(FilterChain chain, DruidDataSource dataSource,
                                                          long maxWaitMillis) throws SQLException {
        long startTime = System.currentTimeMillis();
        DruidPooledConnection conn = chain.dataSource_connect(dataSource, maxWaitMillis);
        long cost = System.currentTimeMillis() - startTime;
        logger.warn("get connection from pool, cost {}ms", cost);
        return conn;
    }

    /**
     * 与数据库创建连接
     * @param chain
     * @param info
     * @return
     * @throws SQLException
     */
    @Override
    public ConnectionProxy connection_connect(FilterChain chain, Properties info) throws SQLException {
        long startTime = System.currentTimeMillis();
        ConnectionProxy connection = super.connection_connect(chain, info);
        logger.info("create connection cost {}ms", System.currentTimeMillis() - startTime);
        return connection;
    }

    /**
     * 执行任意SQL
     * @param chain
     * @param statement
     * @return
     * @throws SQLException
     */
    @Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        long startTime = System.currentTimeMillis();
        boolean firstResult = super.preparedStatement_execute(chain, statement);
        long cost = System.currentTimeMillis() - startTime;
        logger.warn("sql:{} cost {}ms", statement.getSql(), cost);
        return firstResult;
    }

    /**
     * 执行查询
     * @param chain
     * @param statement
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSetProxy preparedStatement_executeQuery(FilterChain chain, PreparedStatementProxy statement)
            throws SQLException {
        long startTime = System.currentTimeMillis();
        ResultSetProxy resultSet = chain.preparedStatement_executeQuery(statement);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 30) {
            logger.warn("sql:{} cost {}ms", statement.getSql(), cost);
        }
        return resultSet;
    }

    /**
     * 执行DML
     * @param chain
     * @param statement
     * @return
     * @throws SQLException
     */
    @Override
    public int preparedStatement_executeUpdate(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        long startTime = System.currentTimeMillis();
        int count = chain.preparedStatement_executeUpdate(statement);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 30) {
            logger.warn("sql:{} cost {}ms", statement.getSql(), cost);
        }
        return count;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql)
            throws SQLException {
        long startTime = System.currentTimeMillis();
        PreparedStatementProxy preparedStatement = chain.connection_prepareStatement(connection, sql);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 10) {
            logger.warn("sql:{} cost {}ms", sql, cost);
        }
        return preparedStatement;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection,
                                                              String sql, int autoGeneratedKeys) throws SQLException {
        long startTime = System.currentTimeMillis();
        PreparedStatementProxy preparedStatement = chain.connection_prepareStatement(connection, sql, autoGeneratedKeys);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 10) {
            logger.warn("prepareStatement:{} cost {}ms", sql, cost);
        }
        return preparedStatement;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection,
                                                              String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        long startTime = System.currentTimeMillis();
        PreparedStatementProxy preparedStatement = chain.connection_prepareStatement(connection, sql, resultSetType, resultSetConcurrency);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 10) {
            logger.warn("prepareStatement:{} cost {}ms", sql, cost);
        }
        return preparedStatement;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection,
                                                              String sql, int resultSetType, int resultSetConcurrency,
                                                              int resultSetHoldability) throws SQLException {
        long startTime = System.currentTimeMillis();
        PreparedStatementProxy preparedStatement = chain.connection_prepareStatement(connection, sql, resultSetType, resultSetConcurrency,
                resultSetHoldability);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 10) {
            logger.warn("prepareStatement:{} cost {}ms", sql, cost);
        }
        return preparedStatement;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection,
                                                              String sql, int[] columnIndexes) throws SQLException {
        long startTime = System.currentTimeMillis();
        PreparedStatementProxy preparedStatement = chain.connection_prepareStatement(connection, sql, columnIndexes);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 10) {
            logger.warn("prepareStatement:{} cost {}ms", sql, cost);
        }
        return preparedStatement;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection,
                                                              String sql, String[] columnNames) throws SQLException {
        long startTime = System.currentTimeMillis();
        PreparedStatementProxy preparedStatement = chain.connection_prepareStatement(connection, sql, columnNames);
        long cost = System.currentTimeMillis() - startTime;
        if (cost > 10) {
            logger.warn("prepareStatement:{} cost {}ms", sql, cost);
        }
        return preparedStatement;
    }
}
