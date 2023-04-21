package com.jjlee.studywebuser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        System.out.println("???");
        ModelAndView modelAndView = new ModelAndView("home/register");
        return modelAndView;
    }
}