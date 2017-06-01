package com.luliru.dubbo.validation;

import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by luliru on 2017/6/1.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public void save(@NotNull Parameter parameter) {
        System.out.println("save:"+parameter);
    }

    @Override
    public void delete(@Min(1) int id) {
        System.out.println("delete:"+id);
    }
}
