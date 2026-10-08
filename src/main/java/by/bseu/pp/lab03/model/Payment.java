package by.bseu.pp.lab03.model;

import java.time.LocalDate;


public class Payment {
    private final String paymentId;
    private final double amount;
    private final LocalDate date;
    private PaymentStatus status;

    public Payment(String paymentId, double amount, LocalDate date) {
        
        if(paymentId==null || paymentId.isBlank()){
            throw new IllegalArgumentException("paymentId НЕ МОЖЕТ БЫТЬ ПУСТЫМ ИЛИ СОДЕРЖАТЬ ТОЛЬКО ПРОБЕЛЫ");
        }

        if(amount<=0.0){
            throw new IllegalArgumentException("amount ДОЛЖЕН БЫТЬ ПОЛОЖИТЕЛЬНЫМ");
        }

        if(date==null){
            throw new IllegalArgumentException("NULL");
        }

        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.status = PaymentStatus.CREATED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void markSuccessful() {
        if(status!=PaymentStatus.CREATED){
            throw new IllegalStateException("status НЕ CREATED");
        }

        this.status=PaymentStatus.SUCCESSFUL;

    }

    public void markFailed() {
       
        if(status!=PaymentStatus.CREATED){
            throw new IllegalStateException("status НЕ CREATED");
        }

        this.status=PaymentStatus.FAILED;  
        
    }

    public boolean isSuccessful() {
        return status==PaymentStatus.SUCCESSFUL;
     }
}
