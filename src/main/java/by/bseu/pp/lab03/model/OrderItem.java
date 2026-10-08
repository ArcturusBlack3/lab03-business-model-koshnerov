package by.bseu.pp.lab03.model;


public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        
        if(product==null){
            throw new IllegalArgumentException("NULL");
        }

        if(quantity<=0){
            throw new IllegalArgumentException("quantity НЕ МОЖЕТ БЫТЬ НЕПОЛОЖИТЕЛЬНЫМ");
        }

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
        return product.calculateAmount(quantity);
    }
}
