package com.spring.bookifybackend.controllers;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.exceptions.RoleNotFoundException;
import com.spring.bookifybackend.exceptions.UserNotFoundException;
import com.spring.bookifybackend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("pageTitle","Add new Role");
        return "admin-role-new-form";
    }

    @PostMapping("roles/save")
    public String saveRole(@RequestParam(value = "update") boolean update, Role role, RedirectAttributes redirectAttributes){
        if(!roleService.isNameUnique(role.getName()) && !update){
            redirectAttributes.addFlashAttribute("error","The role Name is not unique");
            return "redirect:/admin/role/new";
        }
        roleService.save(role);
        redirectAttributes.addFlashAttribute("message","The Role has been saved successfully.");
        return "redirect:/admin/roles";
    }

    @GetMapping("roles/edit/{id}")
    public String editRole(@PathVariable(value = "id") int id , Model model, RedirectAttributes redirectAttributes){
        try{
            Role role = roleService.get(id);
            model.addAttribute("role",role);
            model.addAttribute("pageTitle","Edit Role : id="+id);
            model.addAttribute("update",true);
            return "admin-role-new-form";
        }catch (RoleNotFoundException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/roles";
        }

    }
}
