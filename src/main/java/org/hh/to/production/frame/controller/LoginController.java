package org.hh.to.production.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET  )
    public String doGet() {
        System.out.println("start logining");
        return "login";
    }


}
