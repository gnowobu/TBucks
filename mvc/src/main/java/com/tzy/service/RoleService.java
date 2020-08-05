package com.tzy.service;

import com.tzy.model.Role;
import com.tzy.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public Role getRoleByName(String name){
     return roleDao.getRoleByName(name);
    }
}
