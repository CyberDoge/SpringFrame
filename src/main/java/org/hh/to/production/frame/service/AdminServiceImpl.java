package org.hh.to.production.frame.service;

import org.hh.to.production.frame.model.Role;
import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.repository.RoleRepository;
import org.hh.to.production.frame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        sessionRegistry.getAllPrincipals().stream().forEach(ud -> users.stream()
                .filter(u -> u.getUsername().equals(((UserDetails) ud).getUsername()))
                .findFirst().ifPresent(user -> user.setOnline(true)));
        return users;
    }

    @Override
    public String banUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "No user with such username";
        sessionRegistry.getAllPrincipals().stream()
                .filter((u -> ((UserDetails) u).getUsername().equals(username))).findFirst()
                .ifPresent(t -> sessionRegistry.getAllSessions(t, false).forEach(s -> s.expireNow()));
        if (!user.isEnabled()) return "User is already baned";
        user.setEnabled(false);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String unbanUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "No user with such username";
        if (user.isEnabled()) return "User is not baned";
        user.setEnabled(true);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String addRole(String username, String role) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "No user with such username";
        Set<Role> roles = user.getRoles();
        var newRole = roleRepository.findByRole(role);
        if (newRole == null) return "No such role";
        roles.add(newRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "Success";
    }
}
