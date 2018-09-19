package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Comment;
import org.hh.to.production.frame.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void voteUp(Integer id) {
        commentRepository.findById(id).ifPresent(c -> {
            c.setVoices(c.getVoices() + 1);
            commentRepository.save(c);
        });
    }

    @Override
    public void voteDown(Integer id) {
        commentRepository.findById(id).ifPresent(c -> {
            c.setVoices(c.getVoices() - 1);
            commentRepository.save(c);
        });
    }
}
