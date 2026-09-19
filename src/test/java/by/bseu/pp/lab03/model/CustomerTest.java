package by.bseu.pp.lab03.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void createsActiveCustomerWithGivenData() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);

        assertEquals("C-101", customer.getCode());
        assertEquals("Acme Ltd", customer.getName());
        assertEquals(1000.0, customer.getCreditLimit(), 1e-9);
        assertTrue(customer.isActive());
    }

    @Test
    void rejectsNullCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer(null, "Acme Ltd", 1000.0));
    }

    @Test
    void rejectsBlankCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("   ", "Acme Ltd", 1000.0));
    }

    @Test
    void rejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("C-101", null, 1000.0));
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("C-101", "  ", 1000.0));
    }

    @Test
    void rejectsNegativeCreditLimit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("C-101", "Acme Ltd", -0.01));
    }

    @Test
    void allowsZeroCreditLimit() {
        Customer customer = new Customer("C-101", "Acme Ltd", 0.0);
        assertEquals(0.0, customer.getCreditLimit(), 1e-9);
    }

    @Test
    void renamesCustomer() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        customer.rename("Acme Group");
        assertEquals("Acme Group", customer.getName());
    }

    @Test
    void rejectsBlankNewName() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> customer.rename(" "));
    }

    @Test
    void changesCreditLimit() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        customer.changeCreditLimit(1500.0);
        assertEquals(1500.0, customer.getCreditLimit(), 1e-9);
    }

    @Test
    void rejectsNegativeNewCreditLimit() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> customer.changeCreditLimit(-1));
    }

    @Test
    void canDeactivateAndActivateCustomer() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        customer.deactivate();
        assertFalse(customer.isActive());
        customer.activate();
        assertTrue(customer.isActive());
    }

    @Test
    void activeCustomerCanPlaceOrderBelowLimit() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        assertTrue(customer.canPlaceOrder(999.99));
    }

    @Test
    void activeCustomerCanPlaceOrderExactlyAtLimit() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        assertTrue(customer.canPlaceOrder(1000.0));
    }

    @Test
    void orderAboveLimitIsNotAllowed() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        assertFalse(customer.canPlaceOrder(1000.01));
    }

    @Test
    void inactiveCustomerCannotPlaceOrder() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        customer.deactivate();
        assertFalse(customer.canPlaceOrder(100.0));
    }

    @Test
    void rejectsNegativeOrderTotal() {
        Customer customer = new Customer("C-101", "Acme Ltd", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> customer.canPlaceOrder(-1.0));
    }
}
