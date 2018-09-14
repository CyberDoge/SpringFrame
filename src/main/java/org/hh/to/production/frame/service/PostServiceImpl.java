package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Post;
import org.hh.to.production.frame.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findPost(int id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Post findPost(String header) {
        return postRepository.findPostByHeader(header);
    }

    @Override
    public void deletePost(int id) {
        postRepository.deletePostById(id);
    }

    @Override
    public void deletePost(String header) {
        postRepository.deletePostByHeader(header);
    }

    @Override
    public void renamePost(String oldHeader, String newHeader) {
        var post = postRepository.findPostByHeader(oldHeader);
        post.setHeader(newHeader);
        postRepository.save(post);
    }
}
