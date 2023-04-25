package com.jjlee.studywebuser.mappers;

import com.jjlee.studywebuser.entities.RegisterCodeEntity;
import com.jjlee.studywebuser.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertRegisterCode(RegisterCodeEntity registerCodeEntity);

    UserEntity selectUserByEmail(@Param(value = "email")String email);

    RegisterCodeEntity selectCodeByEmail(@Param(value = "email")String email,
                                         @Param(value = "code") String code,
                                         @Param(value = "salt") String salt);

    int updateRegisterCode(RegisterCodeEntity registerCodeEntity);
}
