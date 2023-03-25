package com.driver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean paymentComplete;

    PaymentMode paymentMode;

    @OneToOne
    @JoinColumn
    @JsonBackReference
    Reservation reservation;

    public Payment(boolean paymentComplete, PaymentMode paymentMode) {
        this.paymentComplete = paymentComplete;
        this.paymentMode = paymentMode;
    }

    public Payment() {
    }

    public Payment(boolean paymentComplete, PaymentMode paymentMode, Reservation reservation) {
        this.paymentComplete = paymentComplete;
        this.paymentMode = paymentMode;
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
