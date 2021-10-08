package com.kaiba.demo.mapstruct;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * UserConverterTest
 * Created by luliru on 2021/10/8.
 */
@Slf4j
public class UserConverterTest {

    public static void main(String[] args) {
        User user = User.builder()
                .id(1)
                .name("张三")
                .createTime("2020-04-01 11:05:07")
                .updateTime(LocalDateTime.now())
                .build();

        UserVO vo = UserConverter.INSTANCE.toVO(user);

        log.info(JSON.toJSONString(vo));
    }
}
