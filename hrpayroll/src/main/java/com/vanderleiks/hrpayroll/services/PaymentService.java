package com.vanderleiks.hrpayroll.services;

import com.vanderleiks.hrpayroll.config.Feign;
import com.vanderleiks.hrpayroll.entities.Payment;
import com.vanderleiks.hrpayroll.entities.Worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

   @Autowired
   private Feign feign;

    public Payment getPayment(Long workerId, int days){
        Worker worker = feign.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}

