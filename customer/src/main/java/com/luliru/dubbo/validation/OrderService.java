package com.luliru.dubbo.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by luliru on 2017/6/1.
 */
public interface OrderService {

    void save(@NotNull Parameter parameter); // 验证参数不为空

    void delete(@Min(1) int id); // 直接对基本类型参数验证
}
