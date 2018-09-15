package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.Post;
import org.hh.to.production.frame.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewsController {
    @Autowired
    PostService postService;

    @RequestMapping(value = {"/news", "/posts"}, method = RequestMethod.GET)
    public String openPage() {
        return "news";
    }

    @RequestMapping(value = "/news/addMore", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> printMorePosts(@RequestBody int lastId) {
        return postService.printPostFrom(lastId);
    }

    @RequestMapping(value = "/news/{id}")
    @ResponseBody
    public Post openPost(@PathVariable int id){
        return postService.findPost(id);
    }
}