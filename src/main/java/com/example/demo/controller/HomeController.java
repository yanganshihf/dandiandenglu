package com.example.demo.controller;

import com.example.demo.annotation.SsoAuth;
import com.example.demo.vo.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @date: 2019-10-07
 * @author: chy
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/Home/Index")
    public ModelAndView Index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("CurrentUser", UserManager.CurrentUser);
        modelAndView.setViewName("test.html");
        return modelAndView;
    }
}
