package org.hh.to.production.frame.repository;

import org.hh.to.production.frame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "user")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findUserById(Integer id);
    List<User> findAll();
}
