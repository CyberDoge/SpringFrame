package org.hh.to.production.frame.repository;

import org.hh.to.production.frame.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "comment")
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
