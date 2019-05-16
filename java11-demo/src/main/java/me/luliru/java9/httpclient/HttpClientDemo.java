package me.luliru.java9.httpclient;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientDemo {

//    public static void main(String[] args) throws Exception{
//        URI testPageURI = new URI("http://127.0.0.1:8080/testPage");
//        CompletableFuture<HttpResponse> nonBlockingResponse = HttpRequest
//                .create(testPageURI)
//                .GET().responseAsync();
//
//        int tries = 0;
//        while(!nonBlockingResponse.isDone() && tries++ < 5){
//            Thread.sleep(5);
//        }
//        if(nonBlockingResponse.isDone()){
//            HttpResponse response = nonBlockingResponse.get();
//            System.out.println("satus code ："+response.statusCode()+"-->"+response.body(HttpResponse.asString()));
//        }else{
//            nonBlockingResponse.cancel(true);
//            System.out.println("cancelling,could not get response");
//        }
//    }
}
