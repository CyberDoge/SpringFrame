package org.hh.to.production.frame.repository;

import org.hh.to.production.frame.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "post")
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Integer id);
    Post findPostByHeader(String header);
    void deletePostByHeader(String header);
    void deletePostById(Integer id);
    List<Post> findPostByIdBetween(Integer from, Integer to);
    Post findFirstByOrderByIdDesc();
}
