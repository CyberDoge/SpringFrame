package org.hh.to.production.frame.repository;

import org.hh.to.production.frame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository("userRepository")
@Table(name = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
