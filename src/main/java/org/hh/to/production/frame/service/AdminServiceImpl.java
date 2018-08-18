package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Role;
import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.repository.RoleRepository;
import org.hh.to.production.frame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String banUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "No user with such username";
        if (!user.isEnabled()) return "User is already baned";
        user.setEnabled(false);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public void makeAdmin(User user) {
        Set<Role> roles = Stream.of(roleRepository.findByRole("USER"), roleRepository.findByRole("ADMIN")).collect(Collectors.toSet());
        user.setRoles(roles);
        userRepository.save(user);
    }
}
