package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String openMainPage(){
        return "index";
    }

    @RequestMapping(value = {"/user/home"}, method = RequestMethod.GET)
    public ModelAndView openHomePage(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelAndView("home", "user", user);
    }
    @RequestMapping(value = {"/access-denied"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(){
        return "access-denied";
    }
}
