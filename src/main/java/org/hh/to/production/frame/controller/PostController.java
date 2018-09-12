package org.hh.to.production.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
    @RequestMapping("/poster/new")
    public String createNewPost() {
        return "create_post";
    }

    @RequestMapping(value = "/poster/save", method = RequestMethod.POST)
    public String savePost(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/poster/saveImage", method = RequestMethod.POST)
    @ResponseBody()
    public String saveImage(HttpServletRequest request) {
        System.out.println("new image");
        return "{\n" +
                "    \"uploaded\": true,\n" +
                "    \"url\": \"https://tgram.ru/wiki/bots/image/everlastingsummerbot.jpg\"\n" +
                "}";
    }

}
