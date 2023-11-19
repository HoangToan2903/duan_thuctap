package com.example.demo.service;

import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAllRole();
    Role findByRoleName(String roleName);
}
