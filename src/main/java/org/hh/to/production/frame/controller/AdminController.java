package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(value = "/admin/userList", method = RequestMethod.POST)
    @ResponseBody
    public List<User> sendUserList() {
        return adminService.findAll();
    }

    @RequestMapping(value = "/admin/banUser", method = RequestMethod.POST)
    @ResponseBody
    public String banUser(@RequestParam() String username) {
        if (username.isEmpty()) return "Empty name";
        sessionRegistry.getAllPrincipals().stream()
                .filter((u -> ((UserDetails) u).getUsername().equals(username))).findFirst()
                .ifPresent(t-> sessionRegistry.getAllSessions(t, false).forEach(s -> s.expireNow()));

        return adminService.banUser(username);
    }
}
