package com.luliru;

import java.util.List;

/**
 * Created by kaiba on 2016/5/6.
 */
public interface UserService {

    public String hello(String id);

    public List<String> fingByIds(Long ... ids);
}
