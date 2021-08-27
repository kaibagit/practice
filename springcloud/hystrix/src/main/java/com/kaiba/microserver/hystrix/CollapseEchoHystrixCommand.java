package com.kaiba.microserver.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 批量执行请求(Request Collapsing)
 * Created by luliru on 2017/3/18.
 */
public class CollapseEchoHystrixCommand extends HystrixCollapser<List<Long>, String, Integer> {
    private static Logger logger = LoggerFactory.getLogger(CollapseEchoHystrixCommand.class);
    private Integer input;

    public CollapseEchoHystrixCommand(Integer input) {
        super(Setter
                .withCollapserKey(HystrixCollapserKey.Factory.asKey("Echo Collapse")));
        this.input = input;
    }

    @Override
    public Integer getRequestArgument() {
        return input;
    }

    @Override
    protected HystrixCommand<List<Long>> createCommand(Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        return new BatchCommand(collapsedRequests);
    }

    @Override
    protected void mapResponseToRequests(List<Long> batchResponse, Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        logger.info("Mapping response to Request");
        int count = 0;
        for (CollapsedRequest<String, Integer> request : collapsedRequests) {
            request.setResponse(batchResponse.get(count++).toString());
        }

    }

    private class BatchCommand extends HystrixCommand<List<Long>> {
        private Collection<CollapsedRequest<String, Integer>> requests;

        public BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
            super(HystrixCommandGroupKey.Factory.asKey("Batch"));
            this.requests = requests;
        }

        @Override
        protected List<Long> run() throws Exception {
            logger.info("Run batch command");
            List<Long> responses = new ArrayList<>();
            for (CollapsedRequest<String, Integer> request : requests) {
                logger.info("Run request: {}", request.getArgument());
                responses.add(request.getArgument().longValue());
            }
            return responses;
        }
    }

    public static void main(String[] args) throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            Future<String> result1 = new CollapseEchoHystrixCommand(1).queue();
            Future<String> result2 = new CollapseEchoHystrixCommand(2).queue();
            Future<String> result3 = new CollapseEchoHystrixCommand(3).queue();
            logger.info(result1.get().getClass()+":"+result1.get());
            logger.info(result2.get().getClass()+":"+result2.get());
            logger.info(result3.get().getClass()+":"+result3.get());
            logger.info(HystrixRequestLog.getCurrentRequest().getExecutedCommands().size() + "");
        } finally {
            context.shutdown();
        }
    }
}



