package com.jjlee.studywebmemo.controllers;

import com.jjlee.studywebmemo.entities.MemoEntity;
import com.jjlee.studywebmemo.services.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    private final MemoService memoService;

    @Autowired
    public HomeController(MemoService memoService) {
        this.memoService = memoService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView("home/index");
        return modelAndView;
    }


    //con -> ser -> map -> m.xml -> map -> ser -> con (entity가 이동)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView postIndex(MemoEntity memoEntity) {
        ModelAndView modelAndView = new ModelAndView("home/index");
        System.out.println(memoEntity.getNickname());
        System.out.println(memoEntity.getText());
        boolean result = this.memoService.write(memoEntity);
        modelAndView.addObject("memoEntity", memoEntity);
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
