package com.jjlee.matzip.mappers;

import com.jjlee.matzip.entities.RegisterContactCodeEntity;
import com.jjlee.matzip.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertRegisterContactCode(RegisterContactCodeEntity registerContactCodeEntity);

    UserEntity selectUserByContact(@Param(value = "contact")String contact);
}
