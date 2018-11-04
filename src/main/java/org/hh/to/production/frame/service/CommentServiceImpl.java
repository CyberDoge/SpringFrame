package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Comment;
import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public boolean voteUp(int id, User user) {
        return commentRepository.findById(id).map(c -> {
            if (c.getVotedUp().stream().noneMatch(user::equals)) {
                if (!c.getVotedDown().remove(user))
                    c.getVotedUp().add(user);
                commentRepository.save(c);
                return true;
            }
            return false;
        }).get();
    }

    @Override
    public boolean voteDown(int id, User user) {
        return commentRepository.findById(id).map(c -> {
            if (c.getVotedDown().stream().noneMatch(user::equals)) {
                if (!c.getVotedUp().remove(user))
                    c.getVotedDown().add(user);
                commentRepository.save(c);
                return true;
            }
            return false;
        }).get();
    }
}
