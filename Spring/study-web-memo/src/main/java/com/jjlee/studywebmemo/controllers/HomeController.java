package com.jjlee.studywebmemo.controllers;

import com.jjlee.studywebmemo.entities.MemoEntity;
import com.jjlee.studywebmemo.models.PagingModel;
import com.jjlee.studywebmemo.services.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView getIndex(@RequestParam(value = "p", defaultValue = "1", required = false) int requestPage) {
        ModelAndView modelAndView = new ModelAndView("home/index");
        PagingModel pagingModel = new PagingModel(
                MemoService.PAGE_COUNT,
                this.memoService.getAllCount(),
                requestPage);
        MemoEntity[] memoEntities = this.memoService.getByPage(pagingModel);
        modelAndView.addObject("memos", memoEntities);
        modelAndView.addObject("pagingModel", pagingModel);
        System.out.println(requestPage);
        return modelAndView;
    }


    //con -> ser -> map -> m.xml -> map -> ser -> con (entity가 이동)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView postIndex(@RequestParam(value = "p", defaultValue = "1", required = false) int requestPage,
                                  MemoEntity memoEntity) {
        boolean result = this.memoService.write(memoEntity);
        ModelAndView modelAndView = this.getIndex(requestPage);
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
