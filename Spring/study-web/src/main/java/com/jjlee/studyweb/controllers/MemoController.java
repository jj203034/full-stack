package com.jjlee.studyweb.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "memo")
public class MemoController {
    @RequestMapping(value = "write", method = RequestMethod.GET)  // get 방식은 문자열로 데이터를 받는다. 주소창에 표시가된다.
    public ModelAndView getWrite() {
        System.out.println("getWrite()");
        ModelAndView modelAndView = new ModelAndView("memo/write");
        return modelAndView;
    }

    @RequestMapping(value = "write", method = RequestMethod.POST) // post 방식은 대용량 데이터를 받는데 적합
    public ModelAndView postWrite(@RequestParam(value = "text", required = false) String text) {
        System.out.printf("postWrite(%s)\n", text);
        ModelAndView modelAndView = new ModelAndView("memo/write");
        return modelAndView;
    }


}
