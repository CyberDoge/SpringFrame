package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.User;

public interface UserService {
    User findUserByUsername(String username);
    User findUserById(Integer id);
    void saveUser(User user);
    String changePassword(String username, String oldPassword, String newPassword);
}
