package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Role;
import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.repository.RoleRepository;
import org.hh.to.production.frame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword) {
        if (oldPassword.isEmpty()|| newPassword.isEmpty()) return "Please fill in all fields";
        if (newPassword.length() < 5) return "Your password must have at least 5 characters";
        User user = userRepository.findByUsername(username);
        assert user != null;
        if(!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) return "Wrong password";
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Success";
    }
}
