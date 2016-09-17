package com.kaiba.demo.ebean;

import com.avaje.ebean.*;

import java.util.List;

/**
 * Created by kaiba on 2016/9/17.
 */
public class SqlSupportTest {

    public static void main(String[] args){
//        String sql
//                = " select order_id, o.status, c.id, c.name,sum(d.order_qty*d.unit_price) as totalAmount"
//                + " from o_order o"
//                + " join o_customer c on c.id = o.kcustomer_id "
//                + " join o_order_detail d on d.order_id = o.id "
//                + " group by order_id, o.status ";
//
//        RawSql rawSql =
//                RawSqlBuilder
//                        .parse(sql)
//                        // map result columns to bean properties
//                        .columnMapping("order_id",  "order.id")
//                        .columnMapping("o.status",  "order.status")
//                        .columnMapping("c.id",      "order.customer.id")
//                        .columnMapping("c.name",    "order.customer.name")
//                        .create();
//
//        Query<OrderAggregate> query = Ebean.find(OrderAggregate.class);
//        query.setRawSql(rawSql)
//// with “parsed” SQL we can add expressions to the
//// where and having clauses etc
//                .where().gt("order.id", 0)
//                .having().gt("totalAmount", 20);

        String sql
                = " select order_id, 'ignoreMe',sum(d.order_qty*d.unit_price) as totalAmount "
                + " from o_order_detail d"
                + " group by order_id ";

        RawSql rawSql =
                RawSqlBuilder
                        .parse(sql)
                        .columnMapping("order_id",  "order.id")
                        .columnMappingIgnore("'ignoreMe'")
                        // don't need this when using column alias
                        .columnMapping("sum(d.order_qty*d.unit_price)","totalAmount")
                        .create();

        Query<OrderAggregate> query = Ebean.find(OrderAggregate.class);
        query.setRawSql(rawSql)
                // after the RawSql query executes Ebean can execute
                // FetchConfig().query() joins ...
                .fetch("order", "status,orderDate",new FetchConfig().query())
                .fetch("order.customer", "name")
                .where().gt("order.id", 0)
                .having().gt("totalAmount", 20)
                .order().desc("totalAmount")
                .setMaxRows(10);
        List<OrderAggregate> list = query.findList();
    }
}
