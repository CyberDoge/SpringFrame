package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.User;

import java.util.List;

public interface AdminService {
    List<User>  findAll();

    String banUser(String username);
    String unbanUser(String username);

    String addRole(String username, String role);
}
