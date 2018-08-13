package org.hh.to.production.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller("/login")
public class LoginController {
    public String doGet() {
        return "login";
    }


}
