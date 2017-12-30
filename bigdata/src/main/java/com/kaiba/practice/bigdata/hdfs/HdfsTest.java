package com.kaiba.practice.bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class HdfsTest {

    private static Configuration conf;

    public static void main(String[] args) throws IOException {
        config();

//        mkdir();

//        uploadFile();

        createFile();

        //测试重命名
        //rename("/user/hadoop/test/d.txt", "/user/hadoop/test/dd.txt");
        //测试删除文件
        //delete("test/dd.txt"); //使用相对路径
        //delete("test1");    //删除目录



        //测试读取文件
//        readFile("test1/d.txt");
    }

    private static void config(){
        conf = new Configuration();
        conf.set("fs.default.name","hdfs://192.168.31.115:9000");
    }

    //创建新文件
    public static void createFile() throws IOException{
        FileSystem fs = FileSystem.get(conf);
        Path dstPath = new Path("/mydir/d2.txt"); //目标路径
        //打开一个输出流
        FSDataOutputStream outputStream = fs.create(dstPath);
        outputStream.write("hello world 世界你好\n".getBytes());
        outputStream.close();
        fs.close();
        System.out.println("文件创建成功！");
    }

    //上传本地文件
    public static void uploadFile() throws IOException{
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path("C:\\boot.asm"); //原路径
        Path dstPath = new Path("/mydir/boot.asm"); //目标路径
        //调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
        fs.copyFromLocalFile(false,srcPath, dstPath);

        //打印文件路径
        System.out.println("Upload to "+conf.get("fs.default.name"));
        System.out.println("------------list files------------"+"\n");
        FileStatus [] fileStatus = fs.listStatus(dstPath);
        for (FileStatus file : fileStatus) {
            System.out.println(file.getPath());
        }
        fs.close();
    }

    //文件重命名
    public static void rename() throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path oldPath = new Path("/mydir/d.txt");
        Path newPath = new Path("/mydir/a.txt");
        boolean isok = fs.rename(oldPath, newPath);
        if(isok){
            System.out.println("rename ok!");
        }else{
            System.out.println("rename failure");
        }
        fs.close();
    }
    //删除文件
    public static void delete() throws IOException{
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("mydir");
        boolean isok = fs.deleteOnExit(path);
        if(isok){
            System.out.println("delete ok!");
        }else{
            System.out.println("delete failure");
        }
        fs.close();
    }

    //创建目录
    public static void mkdir() throws IOException{
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path("/mydir");
        boolean isok = fs.mkdirs(srcPath);
        if(isok){
            System.out.println("create dir ok!");
        }else{
            System.out.println("create dir failure");
        }
        fs.close();
    }

    //读取文件的内容
    public static void readFile(String filePath) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(srcPath);
            IOUtils.copyBytes(in, System.out, 4096, false); //复制到标准输出流
        } finally {
            IOUtils.closeStream(in);
        }
    }

}
