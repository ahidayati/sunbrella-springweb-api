package com.hb.authen_demo.repositories;

import com.hb.authen_demo.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    public List<Role> findAll();

    public Role findByRoleName(String roleName);
}
