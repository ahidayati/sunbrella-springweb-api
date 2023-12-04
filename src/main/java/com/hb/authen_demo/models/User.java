package com.hb.authen_demo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb.authen_demo.validationGroups.RegisterGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Column(name = "firstname", nullable = false)
    @NotBlank(message = "First name cannot be empty")
    private String firstname;

    @Column(nullable = false)
    @NotBlank(message = "Last name cannot be empty")
    private String lastname;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must contain at least 8 characters, a number and a letter",
            groups = RegisterGroup.class
    )
    private String password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_has_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    private List<Role> roles;

    /*@OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    *//*@JsonIgnore*//*
    private List<Order> orders;*/


    public User() {
        this.roles = new ArrayList<Role>();
        //this.orders = new ArrayList<Order>();
    }

    public User(String email, String firstname, String lastname, String password) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public User(String email, String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void addOrder(Order order){
//        this.orders.add(order);
//    }
//
//    public void removeOrder(Order order){
//        this.orders.remove(order);
//    }
}
