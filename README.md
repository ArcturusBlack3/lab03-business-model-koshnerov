# Laboratory Work 3 — Business Object Model

This Maven project is used for Laboratory Work 3 in the **Industrial Programming** course.

## Goal

Complete the object model of a small enterprise order system so that all provided JUnit tests pass.

The task practices:
- classes and objects;
- fields and constructors;
- encapsulation and `private` state;
- getters and meaningful state-changing methods;
- object references and composition;
- `enum` values for object state;
- validation in constructors and methods;
- business rules expressed through object behavior.

## Project structure

- `src/main/java/by/bseu/pp/lab03/model/Customer.java`
- `src/main/java/by/bseu/pp/lab03/model/Product.java`
- `src/main/java/by/bseu/pp/lab03/model/OrderItem.java`
- `src/main/java/by/bseu/pp/lab03/model/Order.java`
- `src/main/java/by/bseu/pp/lab03/model/Payment.java`
- `src/main/java/by/bseu/pp/lab03/model/OrderStatus.java`
- `src/main/java/by/bseu/pp/lab03/model/PaymentStatus.java`
- `src/main/java/by/bseu/pp/lab03/Application.java` — a small manual demonstration;
- `src/test/java/by/bseu/pp/lab03/model/` — provided automated tests.

## Important

Do not rename packages, classes, methods, parameters, or enum values from the starter project.
Do not delete, disable, or modify the provided tests.
Implement the TODO parts only in `src/main/java`.

## Required model

### Customer

A customer has:
- immutable `code`;
- mutable `name`;
- `active` state;
- non-negative `creditLimit`.

A newly created customer is active.
Blank or `null` code/name values are invalid.
Negative credit limits are invalid.

The customer can be activated/deactivated, renamed, and assigned a new non-negative credit limit.
`canPlaceOrder(total)` returns `true` only when the customer is active and `total <= creditLimit`.
A negative order total is invalid.

### Product

A product has:
- immutable `code`;
- mutable `name`;
- non-negative `price`.

Blank or `null` code/name values are invalid.
A zero price is allowed.
`calculateAmount(quantity)` returns `price * quantity`; quantity must be positive.

### OrderItem

An order item contains:
- a non-null `Product`;
- positive `quantity`.

Both fields are immutable after construction.
`getAmount()` delegates the calculation to the associated product.

### Payment

A payment has:
- immutable `paymentId`;
- positive immutable `amount`;
- non-null immutable `date`;
- mutable `PaymentStatus`.

A new payment starts with `PaymentStatus.CREATED`.
It can move once from `CREATED` to either `SUCCESSFUL` or `FAILED`.
A processed payment cannot be processed again.

### Order

A new order:
- belongs to one non-null customer;
- contains no items;
- has status `CREATED`;
- has no payment.

Items can be added only while the order is `CREATED`.
`total()` returns the sum of all order-item amounts.

`confirm()` is allowed only when:
- the order is `CREATED`;
- the order contains at least one item;
- the customer is active;
- the total does not exceed the customer's credit limit.

After successful confirmation the status is `CONFIRMED`.

`cancel()` is allowed for `CREATED` and `CONFIRMED` orders.
Paid or already cancelled orders cannot be cancelled.

`pay(payment)` is allowed only when:
- the order is `CONFIRMED`;
- payment is not `null`;
- payment status is `SUCCESSFUL`;
- payment amount is equal to the order total.

After a successful payment the order status becomes `PAID` and the payment is stored in the order.

## Enum values

`OrderStatus` must contain exactly:
- `CREATED`
- `CONFIRMED`
- `PAID`
- `CANCELLED`

`PaymentStatus` must contain exactly:
- `CREATED`
- `SUCCESSFUL`
- `FAILED`

## Running the project

You may run `Application.main()` to check the model manually while developing.

The final correctness check is the provided JUnit test suite. In IntelliJ IDEA, run all tests under `src/test/java`.

You may also run tests with Maven:

```bash
mvn test
```

The project contains **76 JUnit test methods** that check normal cases, invalid input, boundary values, encapsulation, and state transitions.
