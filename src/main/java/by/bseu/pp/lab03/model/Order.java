package by.bseu.pp.lab03.model;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private Payment payment;

    public Order(Customer customer) {
        
        if(customer==null){
            throw new IllegalArgumentException("NULL");
        }

        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Payment getPayment() {
        return payment;
    }

    public void addItem(Product product, int quantity) {
        
        if(status!=OrderStatus.CREATED){
            throw new IllegalStateException("staus НЕ CREATED");
        }

        OrderItem item= new OrderItem(product, quantity);

        items.add(item);

    }

    public double total() {
        double summ=0.0;
        for(OrderItem item : items){
            summ+=item.getAmount();
        }

        return  summ;

    }

    public void confirm() {
        
        if(status!=OrderStatus.CREATED){
            throw new IllegalStateException("status HE CREATED");
        }

        if(items.isEmpty()){
            throw new IllegalStateException("В ЗАКАЗЕ НЕТ НИ ОДНОЙ ПОЗИЦИИ");
        }

        if(!customer.isActive()){
            throw new IllegalStateException("КЛИЕНТ НЕАКТИВЕН");
        }

        if(total()>customer.getCreditLimit()){
            throw new IllegalStateException("ОБЩАЯ СУММА ЗАКАЗА ПРЕВЫШАЕТ CreditLimit");
        }

        this.status=OrderStatus.CONFIRMED;

    }

    public void cancel() {
        
        if(status==OrderStatus.PAID || status==OrderStatus.CANCELLED){
            throw new IllegalStateException("Отменить можно заказ в состоянии CREATED или CONFIRMED");
        }

        this.status=OrderStatus.CANCELLED;

    }

    public void pay(Payment payment) {
        
        if(status!=OrderStatus.CONFIRMED){
            throw new IllegalStateException("Оплата разрешена только для CONFIRMED-заказа");
        }

        if(payment==null){
            throw new IllegalArgumentException("NULL");
        }

        if(!payment.isSuccessful()){
            throw new IllegalStateException("Платёж не имеет статус SUCCESSFUL");
        }

        if(payment.getAmount()!=total()){
            throw new IllegalArgumentException("Сумма платежа не соответствует total() заказа");
        }

        this.payment=payment;
        this.status=OrderStatus.PAID;

    }

    public boolean isPaid() {
        
        return status==OrderStatus.PAID;

    }
}
