package com.jjlee.matzip.mappers;

import com.jjlee.matzip.entities.RegisterContactCodeEntity;
import com.jjlee.matzip.entities.RegisterEmailCodeEntity;
import com.jjlee.matzip.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertRegisterContactCode(RegisterContactCodeEntity registerContactCodeEntity);

    UserEntity selectUserByContact(@Param(value = "contact")String contact);

    RegisterContactCodeEntity selectRegisterContactCodeByContactCodeSalt(@Param(value = "contact") String contact,
                                                                 @Param(value = "code") String code,
                                                                 @Param(value = "salt") String salt);

    int updateRegisterContactCode (RegisterContactCodeEntity registerContactCode);

    UserEntity selectUserByEmail(@Param(value = "email")String email);

    UserEntity selectUserByNickname(@Param(value = "nickname")String nickname);

    int insertUser(UserEntity user);
    int insertRegisterEmailCode (RegisterEmailCodeEntity registerEmailCode);
}
