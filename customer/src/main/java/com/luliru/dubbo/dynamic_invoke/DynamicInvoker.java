package com.luliru.dubbo.dynamic_invoke;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luliru on 2017/8/11.
 */
public class DynamicInvoker {

    private static Logger logger = LoggerFactory
            .getLogger(DynamicInvoker.class);

    static String applicationName = "DynamicInvoker";

    static String registryProtocol = "dubbo";

    static String registryAddress = "zookeeper://localhost:2181";

    static Integer registryTimeout = 500;

    static String interfaceName = "com.luliru.UserService";

    static String version = null;

    static String serviceMethod = "createDynamically";

    public static void main(String[] args) throws Exception{
        ApplicationConfig application = new ApplicationConfig();
        application.setName(applicationName);

        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(registryProtocol);
        registry.setAddress(registryAddress);
        registry.setTimeout(registryTimeout);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(interfaceName);
        reference.setGeneric(true);
        reference.setCheck(false);
        reference.setVersion(version);

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
        genericService = cache.get(reference);

        Map<String,Object> home = new HashMap<String,Object>();
        home.put("class","com.luliru.Home");
        home.put("name","hangzhou");

        Map<String,Object> user = new HashMap<String, Object>();
        user.put("class","com.luliru.User");
        user.put("id",1L);
        user.put("username","luliru");
        user.put("home",home);

        // 参数类型
        String[] targetParamType = {"com.luliru.User","int"};
        Object[] targetParamValue = {user,1};

        Object remoteDataMap = genericService.$invoke(serviceMethod, targetParamType,targetParamValue);

        String resultValue = JSON.json(remoteDataMap);
        logger.info("最终输出json字符串:" + resultValue);

        //抛出异常
        targetParamValue = new Object[]{user,-1};
        try{
            remoteDataMap = genericService.$invoke(serviceMethod, targetParamType,targetParamValue);
        }catch (GenericException e){
            String exceptionClass = e.getExceptionClass();
            String exceptionMessage = e.getMessage();
            logger.error("",e);
        }
    }
}
