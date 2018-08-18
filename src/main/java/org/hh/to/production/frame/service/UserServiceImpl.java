package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Role;
import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.repository.RoleRepository;
import org.hh.to.production.frame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
