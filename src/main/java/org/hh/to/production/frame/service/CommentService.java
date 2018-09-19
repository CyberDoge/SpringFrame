package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Comment;

public interface CommentService {
    void save(Comment comment);

    void delete(Integer id);

    void voteUp(Integer id);

    void voteDown(Integer id);
}
