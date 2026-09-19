package by.bseu.pp.lab03.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void createsProductWithGivenData() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertEquals("P-10", product.getCode());
        assertEquals("Laptop", product.getName());
        assertEquals(1200.0, product.getPrice(), 1e-9);
    }

    @Test
    void rejectsNullCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(null, "Laptop", 1200.0));
    }

    @Test
    void rejectsBlankCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(" ", "Laptop", 1200.0));
    }

    @Test
    void rejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P-10", null, 1200.0));
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P-10", " ", 1200.0));
    }

    @Test
    void rejectsNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P-10", "Laptop", -1.0));
    }

    @Test
    void allowsZeroPrice() {
        Product product = new Product("P-10", "Promo", 0.0);
        assertEquals(0.0, product.getPrice(), 1e-9);
    }

    @Test
    void renamesProduct() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        product.rename("Business Laptop");
        assertEquals("Business Laptop", product.getName());
    }

    @Test
    void rejectsBlankNewName() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertThrows(IllegalArgumentException.class, () -> product.rename(""));
    }

    @Test
    void changesPrice() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        product.changePrice(1250.0);
        assertEquals(1250.0, product.getPrice(), 1e-9);
    }

    @Test
    void rejectsNegativeNewPrice() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertThrows(IllegalArgumentException.class, () -> product.changePrice(-0.01));
    }

    @Test
    void calculatesAmount() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertEquals(3600.0, product.calculateAmount(3), 1e-9);
    }

    @Test
    void rejectsZeroQuantity() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertThrows(IllegalArgumentException.class, () -> product.calculateAmount(0));
    }

    @Test
    void rejectsNegativeQuantity() {
        Product product = new Product("P-10", "Laptop", 1200.0);
        assertThrows(IllegalArgumentException.class, () -> product.calculateAmount(-1));
    }
}
