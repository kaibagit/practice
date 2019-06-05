package me.luliru.practice.zipkin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.ClientRequestInterceptor;
import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.ClientResponseInterceptor;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerRequestAdapter;
import com.github.kristofa.brave.ServerRequestInterceptor;
import com.github.kristofa.brave.ServerResponseAdapter;
import com.github.kristofa.brave.ServerResponseInterceptor;
import com.github.kristofa.brave.SpanId;
import com.github.kristofa.brave.TraceData;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.twitter.zipkin.gen.Endpoint;

/**
 * BraveTest
 * Created by luliru on 2019-06-04.
 */
public class BraveTest {

    private static HttpSpanCollector collector = null;
    private static Brave brave = null;
    private static Brave brave2 = null;

    private static void braveInit(){
        collector = HttpSpanCollector.create("http://localhost:9411/", new EmptySpanCollectorMetricsHandler());

        brave = new Brave.Builder("appserver").spanCollector(collector).build();
        brave2 = new Brave.Builder("datacenter").spanCollector(collector).build();
    }

    static class Task {
        String name;
        SpanId spanId;
        public Task(String name, SpanId spanId) {
            super();
            this.name = name;
            this.spanId = spanId;
        }
    }

    public static void main(String[] args) throws Exception {
        braveInit();

        ServerRequestInterceptor serverRequestInterceptor = brave.serverRequestInterceptor();

        ClientRequestInterceptor clientRequestInterceptor = brave.clientRequestInterceptor();
        ClientResponseInterceptor clientResponseInterceptor = brave.clientResponseInterceptor();

        serverRequestInterceptor.handle(new ServerRequestAdapterImpl("group_data"));

        ClientRequestAdapterImpl clientRequestAdapterImpl = new ClientRequestAdapterImpl("get_radio_list");
        clientRequestInterceptor.handle(clientRequestAdapterImpl);
        new Thread(()->{
            dcHandle("get_radio_list", clientRequestAdapterImpl.getSpanId());
        }).start();
        Thread.sleep(10);
        clientResponseInterceptor.handle(new ClientResponseAdapterImpl());

        ClientRequestAdapterImpl clientRequestAdapterImpl2 = new ClientRequestAdapterImpl("get_user_list");
        clientRequestInterceptor.handle(clientRequestAdapterImpl2);
        new Thread(()->{
            dcHandle("get_user_list", clientRequestAdapterImpl2.getSpanId());
        }).start();
        Thread.sleep(10);
        clientResponseInterceptor.handle(new ClientResponseAdapterImpl());

        ClientRequestAdapterImpl clientRequestAdapterImpl3 = new ClientRequestAdapterImpl("get_program_list");
        clientRequestInterceptor.handle(clientRequestAdapterImpl3);
        new Thread(()->{
            dcHandle("get_program_list", clientRequestAdapterImpl3.getSpanId());
        }).start();
        Thread.sleep(10);
        clientResponseInterceptor.handle(new ClientResponseAdapterImpl());

        ServerResponseInterceptor serverResponseInterceptor = brave.serverResponseInterceptor();
        serverResponseInterceptor.handle(new ServerResponseAdapterImpl());




        Thread.sleep(3000);
    }

    public static void dcHandle(String spanName, SpanId spanId){
        ServerRequestInterceptor serverRequestInterceptor = brave2.serverRequestInterceptor();
        ServerResponseInterceptor serverResponseInterceptor = brave2.serverResponseInterceptor();
        serverRequestInterceptor.handle(new ServerRequestAdapterImpl(spanName, spanId));
        serverResponseInterceptor.handle(new ServerResponseAdapterImpl());
    }


    static class ServerRequestAdapterImpl implements ServerRequestAdapter {

        Random randomGenerator = new Random();
        SpanId spanId;
        String spanName;

        ServerRequestAdapterImpl(String spanName){
            this.spanName = spanName;
            long startId = randomGenerator.nextLong();
            SpanId spanId = SpanId.builder().spanId(startId).traceId(startId).parentId(startId).build();
            this.spanId = spanId;
        }

        ServerRequestAdapterImpl(String spanName, SpanId spanId){
            this.spanName = spanName;
            this.spanId = spanId;
        }

        @Override
        public TraceData getTraceData() {
            if (this.spanId != null) {
                return TraceData.builder().spanId(this.spanId).build();
            }
            long startId = randomGenerator.nextLong();
            SpanId spanId = SpanId.builder()
                                .spanId(startId)
                                .traceId(startId)
                                .parentId(startId)
                                .build();
            return TraceData.builder().spanId(spanId).build();
        }

        @Override
        public String getSpanName() {
            return spanName;
        }

        @Override
        public Collection<KeyValueAnnotation> requestAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioid", "165646485468486364");
            collection.add(kv);
            return collection;
        }

    }

    static class ServerResponseAdapterImpl implements ServerResponseAdapter {

        @Override
        public Collection<KeyValueAnnotation> responseAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioid", "165646485468486364");
            collection.add(kv);
            return collection;
        }

    }

    static class ClientRequestAdapterImpl implements ClientRequestAdapter {

        String spanName;
        SpanId spanId;

        ClientRequestAdapterImpl(String spanName){
            this.spanName = spanName;
        }

        public SpanId getSpanId() {
            return spanId;
        }

        @Override
        public String getSpanName() {
            return this.spanName;
        }

        @Override
        public void addSpanIdToRequest(SpanId spanId) {
            //记录传输到远程服务
            System.out.println(spanId);
            if (spanId != null) {
                this.spanId = spanId;
                System.out.println(String.format("trace_id=%s, parent_id=%s, span_id=%s", spanId.traceId, spanId.parentId, spanId.spanId));
            }
        }

        @Override
        public Collection<KeyValueAnnotation> requestAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioid", "165646485468486364");
            collection.add(kv);
            return collection;
        }

        @Override
        public Endpoint serverAddress() {
            return null;
        }

    }

    static class ClientResponseAdapterImpl implements ClientResponseAdapter {

        @Override
        public Collection<KeyValueAnnotation> responseAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioname", "火星人1");
            collection.add(kv);
            return collection;
        }

    }
}