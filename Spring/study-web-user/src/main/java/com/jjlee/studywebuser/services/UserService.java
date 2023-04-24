package com.jjlee.studywebuser.services;

import com.jjlee.studywebuser.entities.RegisterCodeEntity;
import com.jjlee.studywebuser.entities.UserEntity;
import com.jjlee.studywebuser.enums.user.RegisterSendEmailResult;
import com.jjlee.studywebuser.mappers.UserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RegisterSendEmailResult registerSendEmail(RegisterCodeEntity registerCodeEntity) throws NoSuchAlgorithmException {

        UserEntity existingUserEntity = this.userMapper.selectUserByEmail(registerCodeEntity.getEmail());
        if (existingUserEntity != null) {
            return RegisterSendEmailResult.FAILURE_EMAIL_DUPLICATE;
        }
        String code = RandomStringUtils.randomNumeric(6);
        String salt = String.format("%s%s%f%f",
                registerCodeEntity.getEmail(),
                code,
                Math.random(),
                Math.random());
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        salt = String.format("%0128x", new BigInteger(1, md.digest()));
        registerCodeEntity.setCode(code)
                .setSalt(salt)
                .setCreatedAt(new Date())
                .setExpiresAt(DateUtils.addMinutes(registerCodeEntity.getCreatedAt(), 10))
                .setVerified(false);
        int insertResult = this.userMapper.insertRegisterCode(registerCodeEntity);
        return insertResult > 0
                ? RegisterSendEmailResult.SUCCESS
                : RegisterSendEmailResult.FAILURE;
    }
}
