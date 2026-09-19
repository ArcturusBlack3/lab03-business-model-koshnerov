package by.bseu.pp.lab03.model;

/**
 * One line of an order: a product and its quantity.
 */
public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        // TODO: validate constructor arguments
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        // TODO: delegate the calculation to Product
        throw new UnsupportedOperationException("TODO");
    }
}
