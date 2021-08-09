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
        model.addAttribute("updateRole",false);
        return "admin-role-new-form";
    }

    @PostMapping("roles/save")
    public String saveRole(@RequestParam(value = "updateRole") boolean updateRole ,
                           @RequestParam(value = "id",required = false) int id,
                           Role role , RedirectAttributes redirectAttributes) throws RoleNotFoundException{
        if(!roleService.isNameUnique(role.getName()) && !updateRole){
            redirectAttributes.addFlashAttribute("error","The Role Name is not unique");
            return "redirect:/admin/roles/new";
        }
        if(updateRole){
            Role tempRole = roleService.get(id);
            int ID = tempRole.getId();
            if(!roleService.isNameUnique(role.getName()) && !(tempRole.getName().equals(role.getName()))){
                redirectAttributes.addFlashAttribute("error","The Role Name is not unique");
                return "redirect:/admin/roles/edit/"+ID;
            }
            role.setId(ID);
            roleService.save(role);
            redirectAttributes.addFlashAttribute("message","The Role with id = "+ID+" has been updated successfully.");
            return "redirect:/admin/roles";
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
            model.addAttribute("updateRole",true);
            return "admin-role-new-form";
        }catch (RoleNotFoundException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/roles";
        }

    }

    @GetMapping("roles/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") int id ,Model model,RedirectAttributes redirectAttributes){
        try{
            Role role = roleService.get(id);
            redirectAttributes.addFlashAttribute("message","The Role with id = "+role.getId()+" and Name = "+role.getName()+" has been deleted");
            roleService.delete(role);
            return "redirect:/admin/roles";
        }catch (RoleNotFoundException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/role";
        }
    }
}
