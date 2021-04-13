package com.vanderleiks.hrpayroll.services;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import javax.print.event.PrintJobListener;

import com.vanderleiks.hrpayroll.entities.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Value("${worker.host}")
    String workerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(Long workerId, int days){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", workerId.toString());
        Worker worker = restTemplate.getForObject(workerHost+"/workers/{id}", Worker.class, uriVariables);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}

class Worker{
    private Long id;
    private String name;
    private Double dailyIncome;
    Worker(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getDailyIncome() {
        return dailyIncome;
    }
    public void setDailyIncome(Double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }
}