package com.jjlee.matzip.services;

import com.jjlee.matzip.entities.RegisterContactCodeEntity;
import com.jjlee.matzip.enums.SendRegisterContactCodeResult;
import com.jjlee.matzip.mappers.UserMapper;
import com.jjlee.matzip.utils.CryptoUtil;
import com.jjlee.matzip.utils.NCloudUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public SendRegisterContactCodeResult sendRegisterContactCodeResult (RegisterContactCodeEntity registerContactCode) {
        if (registerContactCode == null ||
        registerContactCode.getContact() == null ||
        !registerContactCode.getContact().matches("^(010)(\\d{8})$")) {
            return SendRegisterContactCodeResult.FAILURE;
        }
        if (this.userMapper.selectUserByContact(registerContactCode.getContact()) != null) {
            return SendRegisterContactCodeResult.FAILURE_DUPLICATE;
        }
        String code = RandomStringUtils.randomNumeric(6);
        String salt = CryptoUtil.hashSha512(String.format("%s%s%f%f",
                registerContactCode.getContact(),
                code,
                Math.random(),
                Math.random()));
        Date createdAt = new Date();
        Date expiresAt = DateUtils.addMinutes(createdAt, 5);
        registerContactCode.setCode(code)
                .setSalt(salt)
                .setCreatedAt(createdAt)
                .setExpiresAt(expiresAt)
                .setExpired(false);
        NCloudUtil.sendSms(registerContactCode.getContact(), String.format("[맛집 회원가입] 인증번호 [%s]를 입력해 주세요.", registerContactCode.getCode()));
        return this.userMapper.insertRegisterContactCode(registerContactCode) > 0
                ? SendRegisterContactCodeResult.SUCCESS
                : SendRegisterContactCodeResult.FAILURE;
    }
}
