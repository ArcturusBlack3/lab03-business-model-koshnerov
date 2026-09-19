package by.bseu.pp.lab03.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Customer customer(double limit) {
        return new Customer("C-1", "Acme", limit);
    }

    private Product product(String code, double price) {
        return new Product(code, code, price);
    }

    @Test
    void createsEmptyOrderInCreatedStatus() {
        Customer customer = customer(1000.0);
        Order order = new Order(customer);

        assertSame(customer, order.getCustomer());
        assertEquals(OrderStatus.CREATED, order.getStatus());
        assertEquals(0, order.getItemsCount());
        assertEquals(0.0, order.total(), 1e-9);
        assertNull(order.getPayment());
        assertFalse(order.isPaid());
    }

    @Test
    void rejectsNullCustomer() {
        assertThrows(IllegalArgumentException.class, () -> new Order(null));
    }

    @Test
    void addsItemToCreatedOrder() {
        Order order = new Order(customer(1000.0));
        order.addItem(product("P-1", 100.0), 2);
        assertEquals(1, order.getItemsCount());
        assertEquals(200.0, order.total(), 1e-9);
    }

    @Test
    void sumsSeveralItems() {
        Order order = new Order(customer(1000.0));
        order.addItem(product("P-1", 100.0), 2);
        order.addItem(product("P-2", 25.0), 4);
        assertEquals(300.0, order.total(), 1e-9);
    }

    @Test
    void addItemDelegatesValidationToOrderItem() {
        Order order = new Order(customer(1000.0));
        assertThrows(IllegalArgumentException.class, () -> order.addItem(null, 1));
        assertThrows(IllegalArgumentException.class,
                () -> order.addItem(product("P-1", 100.0), 0));
    }

    @Test
    void confirmsValidOrder() {
        Order order = new Order(customer(1000.0));
        order.addItem(product("P-1", 100.0), 2);
        order.confirm();
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void confirmsOrderExactlyAtCreditLimit() {
        Order order = new Order(customer(200.0));
        order.addItem(product("P-1", 100.0), 2);
        assertDoesNotThrow(order::confirm);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void rejectsConfirmingEmptyOrder() {
        Order order = new Order(customer(1000.0));
        assertThrows(IllegalStateException.class, order::confirm);
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void rejectsConfirmingOrderForInactiveCustomer() {
        Customer customer = customer(1000.0);
        customer.deactivate();
        Order order = new Order(customer);
        order.addItem(product("P-1", 100.0), 1);

        assertThrows(IllegalStateException.class, order::confirm);
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void rejectsConfirmingOrderAboveCreditLimit() {
        Order order = new Order(customer(199.99));
        order.addItem(product("P-1", 100.0), 2);

        assertThrows(IllegalStateException.class, order::confirm);
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void cannotAddItemsAfterConfirmation() {
        Order order = new Order(customer(1000.0));
        order.addItem(product("P-1", 100.0), 1);
        order.confirm();

        assertThrows(IllegalStateException.class,
                () -> order.addItem(product("P-2", 20.0), 1));
    }

    @Test
    void canCancelCreatedOrder() {
        Order order = new Order(customer(1000.0));
        order.cancel();
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void canCancelConfirmedOrder() {
        Order order = new Order(customer(1000.0));
        order.addItem(product("P-1", 100.0), 1);
        order.confirm();
        order.cancel();
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void cannotCancelAlreadyCancelledOrder() {
        Order order = new Order(customer(1000.0));
        order.cancel();
        assertThrows(IllegalStateException.class, order::cancel);
    }

    @Test
    void cannotAddItemsToCancelledOrder() {
        Order order = new Order(customer(1000.0));
        order.cancel();
        assertThrows(IllegalStateException.class,
                () -> order.addItem(product("P-1", 100.0), 1));
    }

    @Test
    void cannotPayCreatedOrder() {
        Order order = new Order(customer(1000.0));
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markSuccessful();

        assertThrows(IllegalStateException.class, () -> order.pay(payment));
    }

    @Test
    void rejectsNullPayment() {
        Order order = confirmedOrder(100.0);
        assertThrows(IllegalArgumentException.class, () -> order.pay(null));
    }

    @Test
    void rejectsCreatedPayment() {
        Order order = confirmedOrder(100.0);
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());

        assertThrows(IllegalStateException.class, () -> order.pay(payment));
    }

    @Test
    void rejectsFailedPayment() {
        Order order = confirmedOrder(100.0);
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markFailed();

        assertThrows(IllegalStateException.class, () -> order.pay(payment));
    }

    @Test
    void rejectsPaymentWithWrongAmount() {
        Order order = confirmedOrder(100.0);
        Payment payment = new Payment("PAY-1", 99.99, LocalDate.now());
        payment.markSuccessful();

        assertThrows(IllegalArgumentException.class, () -> order.pay(payment));
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void successfulExactPaymentMarksOrderPaid() {
        Order order = confirmedOrder(100.0);
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markSuccessful();

        order.pay(payment);

        assertEquals(OrderStatus.PAID, order.getStatus());
        assertTrue(order.isPaid());
        assertSame(payment, order.getPayment());
    }

    @Test
    void cannotCancelPaidOrder() {
        Order order = confirmedOrder(100.0);
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markSuccessful();
        order.pay(payment);

        assertThrows(IllegalStateException.class, order::cancel);
    }

    @Test
    void cannotPayOrderTwice() {
        Order order = confirmedOrder(100.0);
        Payment first = new Payment("PAY-1", 100.0, LocalDate.now());
        first.markSuccessful();
        order.pay(first);

        Payment second = new Payment("PAY-2", 100.0, LocalDate.now());
        second.markSuccessful();
        assertThrows(IllegalStateException.class, () -> order.pay(second));
    }

    private Order confirmedOrder(double total) {
        Order order = new Order(customer(total + 100.0));
        order.addItem(product("P-1", total), 1);
        order.confirm();
        return order;
    }
}
