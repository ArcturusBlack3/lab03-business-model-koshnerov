package by.bseu.pp.lab03;

import by.bseu.pp.lab03.model.Customer;
import by.bseu.pp.lab03.model.Order;
import by.bseu.pp.lab03.model.Payment;
import by.bseu.pp.lab03.model.Product;

import java.time.LocalDate;

/**
 * Small manual demo. You may change values here while working on the laboratory task.
 */
public class Application {
    public static void main(String[] args) {
        Customer customer = new Customer("C-100", "Acme Ltd", 5_000.0);
        Product laptop = new Product("P-10", "Laptop", 1_200.0);
        Product mouse = new Product("P-20", "Mouse", 30.0);

        Order order = new Order(customer);
        order.addItem(laptop, 2);
        order.addItem(mouse, 3);
        order.confirm();

        Payment payment = new Payment("PAY-501", order.total(), LocalDate.now());
        payment.markSuccessful();
        order.pay(payment);

        System.out.println("Order total: " + order.total());
        System.out.println("Order status: " + order.getStatus());
    }
}
