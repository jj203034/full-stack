package com.jjlee.matzip.controllers;

import com.jjlee.matzip.entities.RegisterContactCodeEntity;
import com.jjlee.matzip.entities.UserEntity;
import com.jjlee.matzip.enums.*;
import com.jjlee.matzip.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "contactCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getContactCode(RegisterContactCodeEntity registerContactCode) {
        SendRegisterContactCodeResult result = this.userService.sendRegisterContactCodeResult(registerContactCode);
        JSONObject responseObject = new JSONObject() {{
            put("result", result.name().toLowerCase());
        }};
        if (result == SendRegisterContactCodeResult.SUCCESS) {
            responseObject.put("salt", registerContactCode.getSalt());
        }
        return responseObject.toString();
    }

    @RequestMapping(value = "contactCode", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String patchContactCode(RegisterContactCodeEntity registerContactCode) {
        VerifyRegisterContactCodeResult result = this.userService.verifyRegisterContactCode(registerContactCode);
        JSONObject responseObject = new JSONObject() {{
            put("result", result.name().toLowerCase());
        }};
        return responseObject.toString();
    }

    @RequestMapping(value = "emailCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getEmailCount(@RequestParam(value = "email")String email) {
        CheckEmailResult result = this.userService.checkEmail(email);
        JSONObject responseObject = new JSONObject() {{
            put("result", result.name().toLowerCase());
        }};
        return responseObject.toString();
    }

    @RequestMapping(value = "nicknameCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNicknameCount(@RequestParam(value = "nickname")String nickname) {
        CheckNicknameResult result = this.userService.checkNickname(nickname);
        JSONObject responseObject = new JSONObject() {{
            put("result", result.name().toLowerCase());
        }};
        return responseObject.toString();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postRegister(UserEntity user,
                               RegisterContactCodeEntity registerContactCode) {
        RegisterResult result = this.userService.register(user, registerContactCode);
        JSONObject responseObject = new JSONObject() {{
            put("result", result.name().toLowerCase());
        }};
        return responseObject.toString();
    }
}
