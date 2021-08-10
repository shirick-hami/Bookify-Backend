package com.spring.bookifybackend.controllers;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.exceptions.UserNotFoundException;
import com.spring.bookifybackend.services.RoleService;
import com.spring.bookifybackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String listFirstPage(Model model){
        return listUsersByPage(1,model);
    }

    @GetMapping("users/page/{pageNumber}")
    public String listUsersByPage(@PathVariable(name = "pageNumber") int pageNumber , Model model){
        Page<User> userPage = userService.listByPage(pageNumber);
        List<User> userList = userPage.getContent();

        long startCount = (long) (pageNumber - 1) * UserService.USERS_PER_PAGE + 1;
        long endCount = startCount + UserService.USERS_PER_PAGE -1;
        if(endCount > userPage.getTotalElements()){
            endCount = userPage.getTotalElements();
        }

        model.addAttribute("startCount",startCount);
        model.addAttribute("endCount",endCount);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",userPage.getTotalPages());
        model.addAttribute("userList",userList);
        model.addAttribute("totalItems",userPage.getTotalElements());

        return "admin-users";
    }

    @GetMapping("users/new")
    public String newUser(Model model){
        List<Role> rolesList = roleService.listAll();
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("rolesList",rolesList);
        model.addAttribute("pageTitle","Add new User");
        model.addAttribute("updateUser",false);
        return "admin-user-new-form";
    }

    @PostMapping("users/save")
    public String saveUser(@RequestParam(value = "updateUser") boolean updateUser ,
                           @RequestParam(value = "id",required = false) Long id,
                           User user , RedirectAttributes redirectAttributes) throws UserNotFoundException {
        if(!userService.isEmailUnique(user.getEmail()) && !updateUser){
            redirectAttributes.addFlashAttribute("error","The User Email is not unique");
            return "redirect:/admin/users/new";
        }
        if(updateUser){
            User tempUser = userService.get(id);
            Long ID = tempUser.getId();
            if(!userService.isEmailUnique(user.getEmail()) && !(tempUser.getEmail().equals(user.getEmail()))){
                redirectAttributes.addFlashAttribute("error","The User Email is not unique");
                return "redirect:/admin/users/edit/"+ID;
            }
            user.setId(ID);
            userService.save(user);
            redirectAttributes.addFlashAttribute("message","The User with id = "+ID+" has been updated successfully.");
            return "redirect:/admin/users";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("message","The User has been saved successfully.");
        return "redirect:/admin/users";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable(value = "id") Long id ,Model model,RedirectAttributes redirectAttributes){
        try{
            User user = userService.get(id);
            List<Role> rolesList = roleService.listAll();
            model.addAttribute("user",user);
            model.addAttribute("rolesList",rolesList);
            model.addAttribute("pageTitle","Edit User : id="+id);
            model.addAttribute("updateUser",true);
            return "admin-user-new-form";
        }catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/users";
        }

    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id ,Model model,RedirectAttributes redirectAttributes){
        try{
            User user = userService.get(id);
            redirectAttributes.addFlashAttribute("message","The user with id = "+user.getId()+" and email = "+user.getEmail()+" has been deleted");
            userService.delete(user);
            return "redirect:/admin/users";
        }catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/users";
        }
    }

    @GetMapping("users/{id}/enabled/{status}")
    public String updateUserStatus(@PathVariable(value = "id") Long id,
                                   @PathVariable(value = "status") boolean status, RedirectAttributes redirectAttributes){
        userService.updateUserEnabledStatus(id,status);
        if(status){
            redirectAttributes.addFlashAttribute("message","The user with id = "+id+" has been enabled");
        }
        else{
            redirectAttributes.addFlashAttribute("message","The user with id = "+id+" has been disabled");
        }
        return "redirect:/admin/users";
    }
}
