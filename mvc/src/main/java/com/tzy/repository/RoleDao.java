package com.tzy.repository;

import com.tzy.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAllRoles();

    Role getRoleByName(String name);

    void save(Role role);

    boolean delete(Role role);
}
