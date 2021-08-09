package com.spring.bookifybackend.services;

import com.spring.bookifybackend.entities.Role;
import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.exceptions.RoleNotFoundException;
import com.spring.bookifybackend.exceptions.UserNotFoundException;
import com.spring.bookifybackend.repoInterfaces.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listAll(){
        return this.roleRepository.findAll();
    }

    public void save(Role role){
        roleRepository.save(role);
    }

    public void delete(Role role){
        roleRepository.delete(role);
    }

    public boolean isNameUnique(String name){
        Role role = roleRepository.getRoleByName(name);
        return role == null;
    }

    public Role get(int id) throws RoleNotFoundException {
        try {
            return roleRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new RoleNotFoundException("Couldn't find any role with ID = "+id);
        }

    }
}
