package com.jjlee.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "home")
public class HomeController {
    @RequestMapping(value = "portfolio",
                    method = RequestMethod.GET)
    public ModelAndView getPortfolio() {
        ModelAndView modelAndView = new ModelAndView("home/portfolio");
        return modelAndView;
    }
}
