package com.vanderleiks.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.vanderleiks.hrpayroll.entities.Payment;

@Service
public class PaymentService {

    public Payment getPayment(Long workerId, int days){
        return new Payment("Bob", 300.0, days);
    }
    
}
