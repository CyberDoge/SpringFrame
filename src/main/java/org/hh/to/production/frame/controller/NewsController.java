package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.Comment;
import org.hh.to.production.frame.model.Post;
import org.hh.to.production.frame.service.CommentService;
import org.hh.to.production.frame.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

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

    @RequestMapping(value = "/news/{id}/add-comment")
    @ResponseBody
    public Comment addComment(@PathVariable int id, @RequestBody() String commentStr) {
        var post = new Post();
        post.setId(id);
        var comment = new Comment(post, /*((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()*/1,
                commentStr, new Date().getTime(), 0);
        commentService.save(comment);
        return comment;
    }
}
