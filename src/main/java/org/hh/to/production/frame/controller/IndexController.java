package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String openMainPage(){
        return "index";
    }

    @RequestMapping(value = {"/user/home"}, method = RequestMethod.GET)
    public ModelAndView openHomePage(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelAndView("home", "user", userService.findUserByUsername(user.getUsername()));
    }
    @RequestMapping(value = {"/access-denied"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(){
        return "access-denied";
    }
}
