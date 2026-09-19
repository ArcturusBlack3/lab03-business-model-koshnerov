package by.bseu.pp.lab03.model;

/**
 * Represents a customer of the enterprise.
 *
 * <p>Complete all TODO parts without changing the public API of the class.</p>
 */
public class Customer {
    private final String code;
    private String name;
    private boolean active;
    private double creditLimit;

    public Customer(String code, String name, double creditLimit) {
        // TODO: validate constructor arguments
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
        // TODO: validate and change the customer name
        throw new UnsupportedOperationException("TODO");
    }

    public void changeCreditLimit(double newCreditLimit) {
        // TODO: validate and change the credit limit
        throw new UnsupportedOperationException("TODO");
    }

    public void activate() {
        // TODO: activate the customer
        throw new UnsupportedOperationException("TODO");
    }

    public void deactivate() {
        // TODO: deactivate the customer
        throw new UnsupportedOperationException("TODO");
    }

    public boolean canPlaceOrder(double orderTotal) {
        // TODO: return true only for an active customer whose order fits the credit limit
        throw new UnsupportedOperationException("TODO");
    }
}
