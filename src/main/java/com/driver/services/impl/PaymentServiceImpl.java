package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
     Payment payment= new Payment();
     Reservation reservation =reservationRepository2.findById(reservationId).get();

     int total =reservation.getNumberOfHours()*reservation.getSpot().getPricePerHour();
     if(amountSent<total){
         throw new Exception("Insufficient Amount");
     }
     String givenMode = mode.toLowerCase();
     if(!(givenMode.equals("cash")||givenMode.equals("card")||givenMode.equals("upi"))){
         throw new Exception("Payment mode not detected");
     }
     PaymentMode paymentMode;
     if(givenMode.equals("cash")){
         paymentMode= PaymentMode.CASH;
     }else if(givenMode.equals("card")){
         paymentMode = PaymentMode.CARD;
     }else {
         paymentMode = PaymentMode.UPI;
     }
     payment.setPaymentComplete(true);
     payment.setPaymentMode(paymentMode);
     reservation.setPayment(payment);
     reservationRepository2.save(reservation);
     return payment;
    }
}
