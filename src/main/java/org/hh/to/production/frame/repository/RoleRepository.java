package org.hh.to.production.frame.repository;

import org.hh.to.production.frame.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "role")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
