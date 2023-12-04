package com.hb.authen_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "remark", nullable = true)
    private String remark;

    @Column(name = "payment", nullable = false)
    private Boolean payment = false;

    @OneToMany(mappedBy = "order")
    private List<Reservation> reservations;

    @ManyToOne()
    //@JsonIgnore()
    @JoinColumn(nullable = false)
    private User user;


    public Order() {
        this.reservations = new ArrayList<Reservation>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.reservations.remove(reservation);
    }

    public List<Reservation> getReservations(){
        return this.reservations;
    }
}
