package com.spring.bookifybackend.controllers;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.services.RoleService;
import com.spring.bookifybackend.services.UserService;
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

    @GetMapping("users/new")
    public String newUser(Model model){
        List<Role> rolesList = roleService.listAll();
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user",user);
        model.addAttribute("rolesList",rolesList);
        return "admin-user-new-form";
    }

    @PostMapping("users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        if(!userService.isEmailUnique(user.getEmail())){
            redirectAttributes.addFlashAttribute("error","The email is not unique");
            return "redirect:/admin/users/new";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("message","The user has been saved successfully.");
        return "redirect:/admin/users";
    }
}
