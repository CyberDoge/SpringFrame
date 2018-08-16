package org.hh.to.production.frame.controller;

import org.hh.to.production.frame.model.User;
import org.hh.to.production.frame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admin/userList", method = RequestMethod.POST)
    @ResponseBody
    public List<User> sendUserList(){
        return adminService.findAll();
    }
}
