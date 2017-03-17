package com.luliru;

import java.util.List;

/**
 * Created by kaiba on 2016/5/6.
 */
public interface UserService {

    public String hello(String id);

    public String unstableHello(String id, Boolean unstable);

    public List<String> fingByIds(Long... ids);

    public User create(User user, int srouce);
}
