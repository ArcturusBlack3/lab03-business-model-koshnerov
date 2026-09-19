package by.bseu.pp.lab03.model;

/**
 * Represents a product sold by the enterprise.
 */
public class Product {
    private final String code;
    private String name;
    private double price;

    public Product(String code, String name, double price) {
        // TODO: validate constructor arguments
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void rename(String newName) {
        // TODO: validate and change the product name
        throw new UnsupportedOperationException("TODO");
    }

    public void changePrice(double newPrice) {
        // TODO: validate and change the product price
        throw new UnsupportedOperationException("TODO");
    }

    public double calculateAmount(int quantity) {
        // TODO: calculate price * quantity
        throw new UnsupportedOperationException("TODO");
    }
}
