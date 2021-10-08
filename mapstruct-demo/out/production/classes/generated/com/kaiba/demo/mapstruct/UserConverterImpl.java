package com.kaiba.demo.mapstruct;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-08T13:55:32+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserVO toVO(User source) {
        if ( source == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId( source.getId() );
        userVO.setName( source.getName() );
        userVO.setCreateTime( source.getCreateTime() );
        userVO.setUpdateTime( source.getUpdateTime() );

        return userVO;
    }

    @Override
    public User toEntity(UserVO userVO) {
        if ( userVO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userVO.getId() );
        user.setName( userVO.getName() );
        user.setCreateTime( userVO.getCreateTime() );
        user.setUpdateTime( userVO.getUpdateTime() );

        return user;
    }
}
