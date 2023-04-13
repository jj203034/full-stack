package com.jjlee.studyweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "calc")
public class CalcController {
    @RequestMapping(value = "sum")
    public ModelAndView getSum(@RequestParam(value = "a") int a,
                               @RequestParam(value = "b") int b) {
        System.out.println("a는 " + a);
        System.out.println("b는 " + b);
        int result = a+b;
        //return "calc/sum"  // (classpath:)"templates/" + "calc/sum" + ".html"
        ModelAndView modelAndView = new ModelAndView("calc/sum");
        //modelAndView.setViewName("calc/sum");
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @RequestMapping(value = "sub")
    @ResponseBody
    public int getSub(@RequestParam(value = "a") int a,
                      @RequestParam(value = "b") int b) {
        System.out.println("a는 " + a);
        System.out.println("b는 " + b);
        return a - b;
    }

    @RequestMapping(value = "mul")
    @ResponseBody
    public int getMul(@RequestParam(value = "a") int a,
                      @RequestParam(value = "b") int b) {
        System.out.println("a는 " + a);
        System.out.println("b는 " + b);
        return a * b;
    }

    @RequestMapping(value = "div")
    @ResponseBody
    public int getDiv(@RequestParam(value = "a") int a,
                      @RequestParam(value = "b") int b) {
        System.out.println("a는 " + a);
        System.out.println("b는 " + b);
        return a / b;
    }

    @RequestMapping(value = "mod")
    @ResponseBody
    public int getMod(@RequestParam(value = "a") int a,
                      @RequestParam(value = "b") int b) {
        System.out.println("a는 " + a);
        System.out.println("b는 " + b);
        return a % b;
    }
}
