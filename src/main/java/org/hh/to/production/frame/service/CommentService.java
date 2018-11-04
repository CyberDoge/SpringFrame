package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Comment;
import org.hh.to.production.frame.model.User;

public interface CommentService {
    void save(Comment comment);

    void delete(int id);

    boolean voteUp(int id, User user);

    boolean voteDown(int id, User user);
}
