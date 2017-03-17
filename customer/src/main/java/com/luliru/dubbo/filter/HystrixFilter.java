package com.luliru.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * Created by luliru on 2017/3/17.
 */
@Activate(group = Constants.CONSUMER)
public class HystrixFilter implements Filter {

    @Override
    public Result invoke(Invoker invoker, Invocation invocation) throws RpcException {
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation);
        try{
            return (Result)command.execute();
        }catch (Exception e){
            RpcResult result = new RpcResult(e);
            return result;
        }
    }

//    @Override
//    public Result invoke(Invoker invoker, Invocation invocation) throws RpcException {
//        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation);
//        return (Result) command.execute();
//    }

}
