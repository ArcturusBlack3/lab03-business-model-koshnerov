package by.bseu.pp.lab03.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StructureTest {

    @Test
    void customerFieldsArePrivateAndCodeIsFinal() throws Exception {
        assertAllFieldsPrivate(Customer.class);
        assertTrue(Modifier.isFinal(Customer.class.getDeclaredField("code").getModifiers()));
    }

    @Test
    void productFieldsArePrivateAndCodeIsFinal() throws Exception {
        assertAllFieldsPrivate(Product.class);
        assertTrue(Modifier.isFinal(Product.class.getDeclaredField("code").getModifiers()));
    }

    @Test
    void orderItemFieldsArePrivateAndFinal() {
        assertAllFieldsPrivate(OrderItem.class);
        for (Field field : OrderItem.class.getDeclaredFields()) {
            assertTrue(Modifier.isFinal(field.getModifiers()), field.getName() + " must be final");
        }
    }

    @Test
    void paymentCoreDataIsPrivateAndFinal() throws Exception {
        assertAllFieldsPrivate(Payment.class);
        assertTrue(Modifier.isFinal(Payment.class.getDeclaredField("paymentId").getModifiers()));
        assertTrue(Modifier.isFinal(Payment.class.getDeclaredField("amount").getModifiers()));
        assertTrue(Modifier.isFinal(Payment.class.getDeclaredField("date").getModifiers()));
    }

    @Test
    void orderFieldsArePrivate() {
        assertAllFieldsPrivate(Order.class);
    }

    @Test
    void orderStatusContainsRequiredValues() {
        assertArrayEquals(
                new OrderStatus[]{OrderStatus.CREATED, OrderStatus.CONFIRMED, OrderStatus.PAID, OrderStatus.CANCELLED},
                OrderStatus.values());
    }

    @Test
    void paymentStatusContainsRequiredValues() {
        assertArrayEquals(
                new PaymentStatus[]{PaymentStatus.CREATED, PaymentStatus.SUCCESSFUL, PaymentStatus.FAILED},
                PaymentStatus.values());
    }

    private void assertAllFieldsPrivate(Class<?> type) {
        Arrays.stream(type.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .forEach(field -> assertTrue(
                        Modifier.isPrivate(field.getModifiers()),
                        type.getSimpleName() + "." + field.getName() + " must be private"));
    }
}
