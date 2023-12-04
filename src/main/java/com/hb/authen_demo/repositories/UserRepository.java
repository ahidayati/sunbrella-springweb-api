package com.hb.authen_demo.repositories;

import com.hb.authen_demo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    public List<User> findAll();

    public User findByEmail(String email);

    @Query("FROM User u JOIN u.roles AS r WHERE u.email= :email AND r.roleName='Admin'")
    public User findByEmailAndRole(String email);
}
