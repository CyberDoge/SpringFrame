package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Comment;

public interface CommentService {
    void save(Comment comment);

    void delete(int id);

    boolean voteUp(int id, Integer userId);

    boolean voteDown(int id, Integer userId);
}
