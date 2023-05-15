package com.jjlee.matzip.mappers;

import com.jjlee.matzip.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    RecoverContactCodeEntity selectRecoverContactCodeByContactCodeSalt(@Param(value = "contact")String contact,
                                                                       @Param(value = "code")String code,
                                                                       @Param(value = "salt")String salt);

    RegisterContactCodeEntity selectRegisterContactCodeByContactCodeSalt(@Param(value = "contact") String contact,
                                                                 @Param(value = "code") String code,
                                                                 @Param(value = "salt") String salt);

    RegisterEmailCodeEntity selectRegisterEmailCodeByEmailCodeSalt(@Param(value = "email")String email,
                                                                   @Param(value = "code")String code,
                                                                   @Param(value = "salt")String salt);

    RecoverEmailCodeEntity selectRecoverEmailCodeByEmailCodeSalt(RecoverEmailCodeEntity recoverEmailCode);

    UserEntity selectUserByContact(@Param(value = "contact")String contact);

    UserEntity selectUserByEmail(@Param(value = "email")String email);

    UserEntity selectUserByNickname(@Param(value = "nickname")String nickname);

    int deleteRecoverEmailCode(RecoverEmailCodeEntity recoverEmailCode);

    int insertUser(UserEntity user);

    int insertRecoverContactCode (RecoverContactCodeEntity recoverContactCode);

    int insertRecoverEmailCode(RecoverEmailCodeEntity recoverEmailCode);

    int insertRegisterContactCode(RegisterContactCodeEntity registerContactCodeEntity);

    int insertRegisterEmailCode (RegisterEmailCodeEntity registerEmailCode);

    int updateRecoverContactCode (RecoverContactCodeEntity recoverContactCode);

    int updateRecoverEmailCode(RecoverEmailCodeEntity recoverEmailCode);

    int updateRegisterContactCode (RegisterContactCodeEntity registerContactCode);

    int updateRegisterEmailCode(RegisterEmailCodeEntity registerEmailCode);

    int updateUser(UserEntity user);
}
