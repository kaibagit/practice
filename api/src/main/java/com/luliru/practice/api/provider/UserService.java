package com.luliru.practice.api.provider;

import com.luliru.practice.api.dto.User;
import com.luliru.practice.api.exception.ApiException;
import com.luliru.practice.api.exception.UnknownSubException;

import java.util.List;

/**
 * Created by kaiba on 2016/5/6.
 */
public interface UserService {

    String hello(String id);

    String unstableHello(String id, Boolean unstable);

    List<String> fingByIds(Long... ids);

    User create(User user, int srouce);

    User createDynamically(User user, int srouce);

    void throwInnerException(String id);

    void throwUnknownSubException() throws ApiException,UnknownSubException;
}
