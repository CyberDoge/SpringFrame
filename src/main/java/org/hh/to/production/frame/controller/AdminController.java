package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value = "/admin/user-list", method = RequestMethod.POST)
    @ResponseBody
    public List<User> sendUserList() {
        return adminService.findAll();
    }

    @RequestMapping(value = "/admin/ban-user", method = RequestMethod.POST)
    @ResponseBody
    public String banUser(@RequestParam() String username) {
        if (username.isEmpty()) return "Empty name";
        return adminService.banUser(username);
    }

    @RequestMapping(value = "/admin/unban-user", method = RequestMethod.POST)
    @ResponseBody
    public String unbanUser(@RequestParam() String username) {
        if (username.isEmpty()) return "Empty name";
        return adminService.unbanUser(username);
    }

    @RequestMapping(value = "/admin/add-role", method = RequestMethod.POST)
    @ResponseBody
    public String setRole(@RequestParam() String username, @RequestParam() String role) {
        if (username.isEmpty()) return "Empty name";
        return adminService.addRole(username, role);
    }
}
