package com.hb.authen_demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull(message = "Veuillez choisir la ligne")
    private Integer lane;

    @Column(name = "_column")
    private Integer column;

    @Column(name = "reservation_date")
    @Temporal(TemporalType.DATE)
    private Date dateReservation;

    @Column(nullable = false)
    @NotNull(message = "Veuillez indiquer si la commande est acceptée")
    private boolean isAccepted = false;

    @Column(nullable = false)
    @NotNull(message = "Veuillez choisir une option")
    private String equipment;

    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;


    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order command) {
        this.order = command;
    }

    public Integer getLane() {
        return lane;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
