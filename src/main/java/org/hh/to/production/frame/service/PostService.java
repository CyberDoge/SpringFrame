package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Post;

import java.util.List;

public interface PostService {
    void save(Post post);
    Post findPost(int id);
    Post findPost(String header);
    void deletePost(int id);
    void deletePost(String header);
    void renamePost(String oldHeader, String newHeader);
    List<Post> printPostFrom(int last);
}
