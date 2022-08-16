package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.Role;
import com.example.hotelmng.beans.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role){
        return  roleRepository.save(role);
    }
    public void removeRole(Role role){
        roleRepository.delete(role);
    }
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Role findById(Integer roleId){
        return roleRepository.getReferenceById(roleId);
    }
    public Role findByName (String roleName){
        return roleRepository.findByName(roleName);
    }
}
