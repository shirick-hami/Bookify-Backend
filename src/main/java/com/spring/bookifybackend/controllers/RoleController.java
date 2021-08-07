package com.spring.bookifybackend.controllers;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping( "/roles")
    public String listAllRoles(Model model){
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList",roleList);
        return "admin-roles";
    }
}
