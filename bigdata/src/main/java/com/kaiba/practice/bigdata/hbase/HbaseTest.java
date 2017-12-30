//package com.kaiba.practice.bigdata.hbase;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.util.Bytes;
//
//import java.io.IOException;
//
///**
// * Created by luliru on 2017/12/25.
// */
//public class HbaseTest {
//
//    public static void main(String[] args) throws IOException {
//        Configuration conf = HBaseConfiguration.create();
//
//        HTable table = new HTable(conf,"testtable");
//
//        Put put = new Put(Bytes.toBytes("row1"));
//
//        put.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("qua1"),Bytes.toBytes("val1"));
//        put.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("qua2"),Bytes.toBytes("val2"));
//
//        table.put(put);
//    }
//}
