package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.Comment;
import org.hh.to.production.frame.model.Post;
import org.hh.to.production.frame.service.CommentService;
import org.hh.to.production.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/news/{id}/add-comment")
    public Comment addComment(@PathVariable int id, @RequestParam("comment") String commentStr) {
        var post = new Post();
        post.setId(id);
        var user = userService.findUserByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        var comment = new Comment(post, user.getId(), commentStr, new Date().getTime());
        commentService.save(comment);
        comment.setUser(user);
        return comment;
    }

    @PostMapping(value = "/news/{comment-id}/vote-up")
    public boolean upVoteForComment(@PathVariable("comment-id")int id){
        var user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return commentService.voteUp(id, user);
    }

    @PostMapping(value = "/news/{comment-id}/vote-down")
    public boolean downVoteForComment(@PathVariable("comment-id")int id){
        var user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return commentService.voteDown(id, user);
    }
}