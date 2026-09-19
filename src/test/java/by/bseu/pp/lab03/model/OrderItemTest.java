package by.bseu.pp.lab03.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void storesProductAndQuantity() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        OrderItem item = new OrderItem(product, 2);

        assertSame(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    void calculatesAmountThroughProduct() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        OrderItem item = new OrderItem(product, 2);
        assertEquals(2400.0, item.getAmount(), 1e-9);
    }

    @Test
    void rejectsNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(null, 1));
    }

    @Test
    void rejectsZeroQuantity() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(product, 0));
    }

    @Test
    void rejectsNegativeQuantity() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(product, -2));
    }
}
