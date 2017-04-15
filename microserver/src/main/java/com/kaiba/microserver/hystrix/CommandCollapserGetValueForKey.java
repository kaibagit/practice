package com.kaiba.microserver.hystrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * 命令调用合并:HystrixCollapser<br>
 * 命令调用合并允许多个请求合并到一个线程/信号下批量执行。
 * Created by luliru on 2017/2/22.
 */
public class CommandCollapserGetValueForKey extends HystrixCollapser<List<String>, String, Integer> {
    private final Integer key;
    public CommandCollapserGetValueForKey(Integer key) {
        this.key = key;
    }
    @Override
    public Integer getRequestArgument() {
        return key;
    }
    @Override
    protected HystrixCommand<List<String>> createCommand(final Collection<CollapsedRequest<String, Integer>> requests) {
        //创建返回command对象
        return new BatchCommand(requests);
    }
    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, Integer>> requests) {
        int count = 0;
        for (CollapsedRequest<String, Integer> request : requests) {
            //手动匹配请求和响应
            request.setResponse(batchResponse.get(count++));
        }
    }
    private static final class BatchCommand extends HystrixCommand<List<String>> {
        private final Collection<CollapsedRequest<String, Integer>> requests;
        private BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }
        @Override
        protected List<String> run() {
            ArrayList<String> response = new ArrayList<String>();
            for (CollapsedRequest<String, Integer> request : requests) {
                response.add("ValueForKey: " + request.getArgument());
            }
            return response;
        }
    }

    public static void main(String[] args){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Future<String> f1 = new CommandCollapserGetValueForKey(1).queue();
            Future<String> f2 = new CommandCollapserGetValueForKey(2).queue();
            Future<String> f3 = new CommandCollapserGetValueForKey(3).queue();
            Future<String> f4 = new CommandCollapserGetValueForKey(4).queue();
            System.out.println(f1.get());   //ValueForKey: 1
            System.out.println(f2.get());   //ValueForKey: 2
            System.out.println(f3.get());   //ValueForKey: 3
            System.out.println(f4.get());   //ValueForKey: 4

            System.out.println(HystrixRequestLog.getCurrentRequest().getExecutedCommands().size()); //1

            HystrixCommand<?> command = HystrixRequestLog.getCurrentRequest().getExecutedCommands().toArray(new HystrixCommand<?>[1])[0];
            System.out.println(command.getCommandKey().name());     //GetValueForKey
            System.out.println(command.getExecutionEvents().contains(HystrixEventType.COLLAPSED));
            System.out.println(command.getExecutionEvents().contains(HystrixEventType.SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.shutdown();
        }
    }

}
