package by.bseu.pp.lab03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order and protects the allowed order state transitions.
 */
public class Order {
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private Payment payment;

    public Order(Customer customer) {
        // TODO: validate customer
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
        // TODO: items may be added only while the order is CREATED
        throw new UnsupportedOperationException("TODO");
    }

    public double total() {
        // TODO: sum amounts of all order items
        throw new UnsupportedOperationException("TODO");
    }

    public void confirm() {
        // TODO: confirm a non-empty CREATED order for an active customer within the credit limit
        throw new UnsupportedOperationException("TODO");
    }

    public void cancel() {
        // TODO: allow cancellation only before payment
        throw new UnsupportedOperationException("TODO");
    }

    public void pay(Payment payment) {
        // TODO: accept only a successful payment for the exact total of a CONFIRMED order
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isPaid() {
        // TODO: return true only for PAID order
        throw new UnsupportedOperationException("TODO");
    }
}
