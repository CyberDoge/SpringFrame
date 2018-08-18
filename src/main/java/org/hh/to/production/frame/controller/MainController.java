package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String openMainPage() {
        return "index";
    }


    @RequestMapping(value = {"/user/home", "/user/"}, method = RequestMethod.GET)
    public ModelAndView openHomePage() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelAndView("home", "user", userService.findUserByUsername(user.getUsername()));
    }

    @RequestMapping(value = {"/admin/home", "/admin/"}, method = RequestMethod.GET)
    public ModelAndView openAdminPage(HttpServletRequest request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelAndView("admin-home", "user", userService.findUserByUsername(user.getUsername()));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return "redirect:/user/home";
    }
}
