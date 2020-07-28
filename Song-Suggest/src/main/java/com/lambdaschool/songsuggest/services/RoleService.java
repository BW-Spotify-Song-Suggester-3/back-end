package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.models.Role;

import java.util.List;


public interface RoleService {

    List<Role> findAll();


    Role findRoleById(long id);


    Role save(Role role);


    Role findByName(String name);


    void deleteAll();


    Role update(
            long id,
            Role role);
}