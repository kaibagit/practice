package com.kaiba.microserver.hystrix;

import com.netflix.hystrix.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luliru on 2017/2/22.
 */
public class TagQueryCommand extends HystrixCommand<List<String>> {
    // queryTags()的入参
    int groupId;
    // dubbo的实现接口
    TagService remoteServiceRef;
    // 构造方法用来进行方法参数传递
    protected TagQueryCommand(int groupId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TagService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("TagQueryCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("TagServicePool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        .withCircuitBreakerEnabled(true)
                ));
        this.groupId = groupId;
//        this.remoteServiceRef = ApplicationContextHelper.getBean(TagService.class);
    }
    // 我们调用远程方法定义在这里面
    @Override
    protected List<String> run() throws Exception {
        return remoteServiceRef.queryTags(groupId);
    }
    // 降级方式
    @Override
    protected List<String> getFallback() {
        return Collections.emptyList();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 带有隔离机制和熔断器的远程调用
        List<String> tags = new TagQueryCommand(1).execute();
        Future<List<String>> f = new TagQueryCommand(1).queue();
        List<String> list = f.get();
    }
}
