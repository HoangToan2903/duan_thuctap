package com.example.demo.service.impl;


import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRole(){
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
