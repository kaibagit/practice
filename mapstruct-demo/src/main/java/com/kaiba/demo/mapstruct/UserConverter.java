package com.kaiba.demo.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * UserConverter
 * Created by luliru on 2021/10/8.
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserVO toVO(User source);
    User toEntity(UserVO userVO);
}
