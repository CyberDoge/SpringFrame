package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.User;

import java.util.List;

public interface AdminService {
    List<User>  findAll();
    void banUser(String username);
    void makeAdmin(User user);
}
