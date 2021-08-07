package com.spring.bookifybackend.controllers;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.services.RoleService;
import com.spring.bookifybackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String home(){
        return "admin-home";
    }

    @GetMapping( "/users")
    public String listAllUsers(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList",userList);
        return "admin-users";
    }

    @GetMapping( "/roles")
    public String listAllRoles(Model model){
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList",roleList);
        return "admin-roles";
    }
}
