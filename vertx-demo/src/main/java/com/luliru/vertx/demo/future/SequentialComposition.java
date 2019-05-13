package com.luliru.vertx.demo.future;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/**
 * Created by luliru on 2018/7/17.
 */
public class SequentialComposition {

    public static void main(String[] args){

    }

    static void compose(){
        Vertx vertx = Vertx.vertx();

        FileSystem fs = vertx.fileSystem();
        Future<Void> startFuture = Future.future();

        Future<Void> fut1 = Future.future();
        fs.createFile("/foo", fut1.completer());

        fut1.compose(v -> {
            // When the file is created (fut1), execute this:
            Future<Void> fut2 = Future.future();
            fs.writeFile("/foo", Buffer.buffer(), fut2.completer());
            return fut2;
        }).compose(v -> {
                    // When the file is written (fut2), execute this:
                    fs.move("/foo", "/bar", startFuture.completer());
                },
                // mark startFuture it as failed if any step fails.
                startFuture);
    }
}
