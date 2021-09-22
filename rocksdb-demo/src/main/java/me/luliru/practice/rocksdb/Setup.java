package me.luliru.practice.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

import java.util.Arrays;
import java.util.List;

/**
 * Setup
 * Created by luliru on 9/21/21.
 */
public class Setup {

    static {
        RocksDB.loadLibrary();
    }

    private static final String dbPath   = "/Users/luliru/develop/rocksdb-data/";

    public static RocksDB open() throws RocksDBException {
        Options options = new Options().setCreateIfMissing(true);
        RocksDB rocksDB = RocksDB.open(options, dbPath);
        return rocksDB;
    }

    public static void basicOps() throws RocksDBException {
        RocksDB rocksDB = open();

        // 简单key-value
        byte[] key = "Hello".getBytes();
        rocksDB.put(key, "World".getBytes());

        System.out.println(new String(rocksDB.get(key)));

        // 删除一个key
        rocksDB.delete(key);
        System.out.println("after remove key:" + new String(key));
    }

    public static void multiGet() throws RocksDBException {
        RocksDB rocksDB = open();

        List<byte[]> keys = Arrays.asList("SecondKey".getBytes(), "missKey".getBytes());
        List<byte[]> values = rocksDB.multiGetAsList(keys);
        for (int i = 0; i < keys.size(); i++) {
            System.out.println("multiGet " + new String(keys.get(i)) + ":" + (values.get(i) != null ? new String(values.get(i)) : null));
        }

        // 打印全部[key - value]
        RocksIterator iter = rocksDB.newIterator();
        for (iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iterator key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
        }
    }

    public static void seek() throws RocksDBException {
        RocksDB rocksDB = open();

        rocksDB.put("mykey".getBytes(), "myvalue".getBytes());
        rocksDB.put("mykey2".getBytes(), "myvalue2".getBytes());
        rocksDB.put("yourkey".getBytes(), "yourkey".getBytes());

        RocksIterator iter = rocksDB.newIterator();
        for (iter.seek("mykey2".getBytes()); iter.isValid(); iter.next()) {
            System.out.println("iterator key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
        }
    }

    public static void main(String[] args) throws RocksDBException {
        seek();
    }
}
