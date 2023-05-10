package com.jjlee.matzip.services;

import com.jjlee.matzip.entities.RegisterEmailCodeEntity;
import com.jjlee.matzip.entities.RegisterContactCodeEntity;
import com.jjlee.matzip.entities.UserEntity;
import com.jjlee.matzip.enums.*;
import com.jjlee.matzip.mappers.UserMapper;
import com.jjlee.matzip.utils.CryptoUtil;
import com.jjlee.matzip.utils.NCloudUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;

@Service
public class UserService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final UserMapper userMapper;

    @Autowired
    public UserService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine, UserMapper userMapper) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
        this.userMapper = userMapper;
    }

    public SendRegisterContactCodeResult sendRegisterContactCodeResult(RegisterContactCodeEntity registerContactCode) {
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

    public VerifyRegisterContactCodeResult verifyRegisterContactCode(RegisterContactCodeEntity registerContactCode) {
        RegisterContactCodeEntity existingRegisterContactCodeEntity = this.userMapper.selectRegisterContactCodeByContactCodeSalt(registerContactCode.getContact(), registerContactCode.getCode(), registerContactCode.getSalt());

        if (existingRegisterContactCodeEntity == null) {
            return VerifyRegisterContactCodeResult.FAILURE;
        } else if (new Date().compareTo(existingRegisterContactCodeEntity.getExpiresAt()) > 0) {
            return VerifyRegisterContactCodeResult.FAILURE_EXPIRED;
        }
        existingRegisterContactCodeEntity.setExpired(true);
        return this.userMapper.updateRegisterContactCode(existingRegisterContactCodeEntity) > 0
                ? VerifyRegisterContactCodeResult.SUCCESS
                : VerifyRegisterContactCodeResult.FAILURE;
    }

    public CheckEmailResult checkEmail(String email) {
        return this.userMapper.selectUserByEmail(email) == null
                ? CheckEmailResult.OKAY
                : CheckEmailResult.DUPLICATE;
    }

    public CheckNicknameResult checkNickname(String nickname) {
        return this.userMapper.selectUserByNickname(nickname) == null
                ? CheckNicknameResult.OKAY
                : CheckNicknameResult.DUPLICATE;
    }

    public RegisterResult register(UserEntity user, RegisterContactCodeEntity registerContactCode) throws MessagingException {
        if (this.userMapper.selectUserByEmail(user.getEmail()) != null) {
            return RegisterResult.FAILURE_DUPLICATE_EMAIL;
        }
        if (this.userMapper.selectUserByContact(user.getContact()) != null) {
            return RegisterResult.FAILURE_DUPLICATE_CONTACT;
        }
        if (this.userMapper.selectUserByNickname(user.getNickname()) != null) {
            return RegisterResult.FAILURE_DUPLICATE_NICKNAME;
        }
        registerContactCode = this.userMapper.selectRegisterContactCodeByContactCodeSalt(registerContactCode.getContact(), registerContactCode.getCode(), registerContactCode.getSalt());
        if (registerContactCode == null || !registerContactCode.isExpired()) {
            System.out.println(registerContactCode.isExpired());
            return RegisterResult.FAILURE;
        }
        user.setPassword(CryptoUtil.hashSha512(user.getPassword()));
        user.setStatus("EMAIL_PENDING");
        user.setAdmin(false);

        RegisterEmailCodeEntity registerEmailCode = new RegisterEmailCodeEntity();
        registerEmailCode.setEmail(user.getEmail());
        registerEmailCode.setCode(RandomStringUtils.randomNumeric(6));
        registerEmailCode.setSalt(CryptoUtil.hashSha512(String.format("%s%s%f%f",
                registerEmailCode.getEmail(),
                registerEmailCode.getCode(),
                Math.random(),
                Math.random())));
        registerEmailCode.setCreatedAt(new Date());
        registerEmailCode.setExpiresAt(DateUtils.addHours(registerEmailCode.getCreatedAt(), 1));
        registerEmailCode.setExpired(false);


        String url = String.format("http://localhost:6795/user/emailCode?email=$s&code=$s&salt=%s&",
                registerEmailCode.getEmail(),
                registerEmailCode.getCode(),
                registerEmailCode.getSalt());
        Context context = new Context();
        context.setVariable("url", url);

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject("[맛집 회원가입] 이메일 인증");
        mimeMessageHelper.setFrom("2dlwowns@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(this.springTemplateEngine.process("_registerEmail", context), true);
        this.javaMailSender.send(mimeMessage);

        return this.userMapper.insertUser(user) > 0 && this.userMapper.insertRegisterEmailCode(registerEmailCode) > 0
                ? RegisterResult.SUCCESS
                : RegisterResult.FAILURE;
    }
}
