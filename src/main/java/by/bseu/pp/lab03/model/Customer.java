package by.bseu.pp.lab03.model;


public class Customer {
    private final String code;
    private String name;
    private boolean active;
    private double creditLimit;

    public Customer(String code, String name, double creditLimit) {

        if(code==null || code.isBlank()){
            throw new IllegalArgumentException("code НЕ МОЖЕТ БЫТЬ ПУСТЫМ ИЛИ СОДЕРЖАТЬ ТОЛЬКО ПРОБЕЛЫ");
        }

        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("name НЕ МОЖЕТ БЫТЬ ПУСТЫМ ИЛИ СОДЕРЖАТЬ ТОЛЬКО ПРОБЕЛЫ");
        }

        if(creditLimit<0.0){
            throw new IllegalArgumentException("creditLimit НЕ МОЖЕТ БЫТЬ ОТРИЦАТЕЛЬНЫМ");
        }

        this.code = code;
        this.name = name;
        this.creditLimit = creditLimit;
        this.active = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void rename(String newName) {
        
        if(newName==null || newName.isBlank()){
            throw new IllegalArgumentException("newName НЕ МОЖЕТ БЫТЬ ПУСТЫМ ИЛИ СОДЕРЖАТЬ ТОЛЬКО ПРОБЕЛЫ");
        }
        this.name=newName;
    }

    public void changeCreditLimit(double newCreditLimit) {
        
        if(newCreditLimit<0.0){
            throw new IllegalArgumentException("newCreditLimit НЕ МОЖЕТ БЫТЬ ОТРИЦАТЕЛЬНЫМ");
        }

        this.creditLimit=newCreditLimit;
    }

    public void activate() {
        
        this.active=true;
    }

    public void deactivate() {
        
        this.active=false;
    }

    public boolean canPlaceOrder(double orderTotal) {
        
        if(orderTotal<0.0){
            throw new IllegalArgumentException("orderTotal НЕ МОЖЕТ БЫТЬ ОТРИЦАТЕЛЬНЫМ");
        }

        return active && orderTotal<=creditLimit;
    }
}
