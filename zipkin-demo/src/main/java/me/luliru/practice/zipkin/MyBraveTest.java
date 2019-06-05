package me.luliru.practice.zipkin;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.http.HttpSpanCollector;

/**
 * MyBraveTest
 * Created by luliru on 2019-06-03.
 */
public class MyBraveTest {

    public static void main(String[] args){
        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
                .compressionEnabled(false)// 默认false，span在transport之前是否会被gzipped
                .connectTimeout(5000)
                .flushInterval(1)
                .readTimeout(6000)
                .build();
        HttpSpanCollector collector = HttpSpanCollector.create("http://localhost:9411", config, new EmptySpanCollectorMetricsHandler());

        // 初始化 Brave
        Brave.Builder builder = new Brave.Builder("serviceName");
        builder.spanCollector(collector);
        Brave brave = builder.build();
    }

    public static void test(){
//        // 1.构建客户端发送工具
//        Sender sender = OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
//        // 2.构建异步reporter
//        AsyncReporter asyncReporter = AsyncReporter.builder(sender)
//                .closeTimeout(500, TimeUnit.MILLISECONDS)
//                .build(SpanBytesEncoder.JSON_V2);
//        // 3.构建tracing上下文
//        Tracing tracing = Tracing.newBuilder()
//                .localServiceName("myService")
//                .spanReporter(asyncReporter)
//                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY,"shuang"))
//                .currentTraceContext(ThreadContextCurrentTraceContext.create())
//                .build();
//        // 4.使用tracer创建span并操作start()和finish()方法
//        Tracer tracer = tracing.tracer();
//        Span span = tracer.newTrace().name("total").start();
//
//        Span action_1 = tracer.newChild(span.context()).name("action-1").start();
//        try {
//            Thread.sleep(500);
//        }finally {
//            action_1.finish();
//        }
//
//        Span action_2 = tracer.newChild(span.context()).name("action-2").start();
//        try {
//            Thread.sleep(500);
//        }finally {
//            action_2.finish();
//        }
//
//        try {
//            Thread.sleep(2000);
//        }finally {
//            span.finish();
//        }
    }
}
