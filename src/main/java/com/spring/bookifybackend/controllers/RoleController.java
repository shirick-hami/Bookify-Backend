package com.spring.bookifybackend.controllers;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("roles/new")
    public String newRole(Model model){
        Role role = new Role();
        model.addAttribute("role",role);
        return "admin-role-new-form";
    }

    @PostMapping("roles/save")
    public String saveRole(Role role, RedirectAttributes redirectAttributes){
        roleService.save(role);
        redirectAttributes.addFlashAttribute("message","The Role has been saved successfully.");
        return "redirect:/admin/roles";
    }
}
