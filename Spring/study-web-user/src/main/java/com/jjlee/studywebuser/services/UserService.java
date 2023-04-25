package com.jjlee.studywebuser.services;

import com.jjlee.studywebuser.entities.RegisterCodeEntity;
import com.jjlee.studywebuser.entities.UserEntity;
import com.jjlee.studywebuser.enums.user.RegisterSendEmailResult;
import com.jjlee.studywebuser.enums.user.RegisterVerifyEmailResult;
import com.jjlee.studywebuser.mappers.UserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Service
public class UserService {
    private final JavaMailSender javaMailSender;
    private final UserMapper userMapper;

    @Autowired
    public UserService(JavaMailSender javaMailSender, UserMapper userMapper) {
        this.javaMailSender = javaMailSender;
        this.userMapper = userMapper;
    }

    public RegisterVerifyEmailResult registerVerifyEmail(RegisterCodeEntity registerCodeEntity) {
        Date now = new Date();
        RegisterCodeEntity existingRegisterCodeEntity = this.userMapper.selectCodeByEmail(registerCodeEntity.getEmail(),registerCodeEntity.getCode(),registerCodeEntity.getSalt());
        if (existingRegisterCodeEntity == null) {
            return RegisterVerifyEmailResult.FAILURE;
        } else if (existingRegisterCodeEntity != null && now.compareTo(existingRegisterCodeEntity.getExpiresAt()) > 0) {
            return RegisterVerifyEmailResult.FAILURE_EXPIRED;
        }
        existingRegisterCodeEntity.setVerified(true);
        return this.userMapper.updateRegisterCode(existingRegisterCodeEntity) > 0
                ? RegisterVerifyEmailResult.SUCCESS
                : RegisterVerifyEmailResult.FAILURE;
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
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(registerCodeEntity.getEmail());
        mail.setSubject("[study-web] 인증번호");
        mail.setText(code);
        this.javaMailSender.send(mail);

        return insertResult > 0
                ? RegisterSendEmailResult.SUCCESS
                : RegisterSendEmailResult.FAILURE;
    }
}
