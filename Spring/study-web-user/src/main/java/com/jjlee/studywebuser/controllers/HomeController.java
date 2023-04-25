package com.jjlee.studywebuser.controllers;

import com.jjlee.studywebuser.entities.RegisterCodeEntity;
import com.jjlee.studywebuser.enums.user.RegisterSendEmailResult;
import com.jjlee.studywebuser.services.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        System.out.println("???");
        ModelAndView modelAndView = new ModelAndView("home/register");
        return modelAndView;
    }

    @RequestMapping(value = "registerSendEmail", method = RequestMethod.POST)
    @ResponseBody
    public String postRegisterSendEmail(RegisterCodeEntity registerCodeEntity) throws NoSuchAlgorithmException {
        RegisterSendEmailResult result = this.userService.registerSendEmail(registerCodeEntity);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result", result.name().toLowerCase());
        if (result == RegisterSendEmailResult.SUCCESS) {
            responseObject.put("salt", registerCodeEntity.getSalt());
        }
        return responseObject.toString();
    }
}