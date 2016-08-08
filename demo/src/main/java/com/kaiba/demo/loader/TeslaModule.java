package com.kaiba.demo.loader;

/**
 * Created by luliru on 2016/8/8.
 */
public interface TeslaModule {

    /**
     * 模块的启动级别，决定了模块启动关闭的顺序
     *
     * @return 模块的启动级别
     */
    int getStartLevel();

    /**
     * 模块的初始化方法
     *
     * @throws Exception
     */
    void init(Object... args) throws Exception;

    /**
     * 模块的启动入口
     *
     * @throws Exception
     */
    void start(Object... args) throws Exception;

    /**
     * 模块的关闭入口
     *
     * @throws Exception
     */
    void stop(Object... args) throws Exception;

}
