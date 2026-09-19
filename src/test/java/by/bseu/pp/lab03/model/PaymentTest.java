package by.bseu.pp.lab03.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void createsPaymentInCreatedStatus() {
        LocalDate date = LocalDate.of(2026, 9, 19);
        Payment payment = new Payment("PAY-1", 500.0, date);

        assertEquals("PAY-1", payment.getPaymentId());
        assertEquals(500.0, payment.getAmount(), 1e-9);
        assertEquals(date, payment.getDate());
        assertEquals(PaymentStatus.CREATED, payment.getStatus());
        assertFalse(payment.isSuccessful());
    }

    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment(null, 100.0, LocalDate.now()));
    }

    @Test
    void rejectsBlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment(" ", 100.0, LocalDate.now()));
    }

    @Test
    void rejectsZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("PAY-1", 0.0, LocalDate.now()));
    }

    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("PAY-1", -10.0, LocalDate.now()));
    }

    @Test
    void rejectsNullDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("PAY-1", 100.0, null));
    }

    @Test
    void marksPaymentSuccessful() {
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markSuccessful();
        assertEquals(PaymentStatus.SUCCESSFUL, payment.getStatus());
        assertTrue(payment.isSuccessful());
    }

    @Test
    void marksPaymentFailed() {
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markFailed();
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
        assertFalse(payment.isSuccessful());
    }

    @Test
    void successfulPaymentCannotBeProcessedAgain() {
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markSuccessful();
        assertThrows(IllegalStateException.class, payment::markFailed);
    }

    @Test
    void failedPaymentCannotBeProcessedAgain() {
        Payment payment = new Payment("PAY-1", 100.0, LocalDate.now());
        payment.markFailed();
        assertThrows(IllegalStateException.class, payment::markSuccessful);
    }
}
