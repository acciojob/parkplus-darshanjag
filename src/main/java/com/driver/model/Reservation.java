package com.driver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int numberOfHours;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    Spot spot;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    User user;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonManagedReference
    Payment payment;

    public Reservation() {
    }

    public Reservation(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Reservation(int numberOfHours, Spot spot) {
        this.numberOfHours = numberOfHours;
        this.spot = spot;
    }

    public Reservation(int numberOfHours, Spot spot, User user) {
        this.numberOfHours = numberOfHours;
        this.spot = spot;
        this.user = user;
    }

    public Reservation(int numberOfHours, Payment payment) {
        this.numberOfHours = numberOfHours;
        this.payment = payment;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
