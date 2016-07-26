package com.luliru.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaiba on 2016/5/20.
 */
@Activate(
        group = {"provider"},
        order = 22222222
)
public class ExceptionLogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLogFilter.class);

    private static int num = 0;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        num = num+1;
//        System.out.println(num);
        try {
            Result result = invoker.invoke(invocation);
            if (result.hasException()) {
                System.out.println(num);
                log.error("ExceptionLogFilter invoke result exception:",result.getException());
            }
            return result;
        } catch (RuntimeException e) {
            System.out.println(num);
            log.error("ExceptionLogFilter invoke error",e);

            throw e;
        }
    }
}