package com.hb.authen_demo.services;

import com.hb.authen_demo.models.Role;
import com.hb.authen_demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public void createRole(Role role){
        roleRepository.save(role);
    }
}
