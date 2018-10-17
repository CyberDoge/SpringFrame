package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.Post;
import org.hh.to.production.frame.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NewsController {
    private final PostService postService;

    @Autowired
    public NewsController(PostService postService) {
        this.postService = postService;
    }


    @RequestMapping(value = {"/news", "/posts"}, method = RequestMethod.GET)
    public String openPage() {
        return "news";
    }

    @RequestMapping(value = "/news/get-more", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> printMorePosts(@RequestBody int lastId) {
        return postService.printPostFrom(lastId);
    }

    @RequestMapping(value = "/news/{id}")
    public ModelAndView openPost(@PathVariable int id) {
        var post = postService.findPost(id);
        if (post == null) return new ModelAndView("404");
        return new ModelAndView("post", "post", post);
    }
}
