package org.hh.to.production.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
    @RequestMapping("/poster/new")
    public String createNewPost() {
        return "create_post";
    }

    @RequestMapping(value = "/poster/save", method = RequestMethod.POST)
    public String savePost(HttpServletRequest request) {
        return "redirect://index";
    }

}
