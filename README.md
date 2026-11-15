# Food Delivery System - Delivery Module

This is a minimal Spring Boot implementation of the Delivery Module from the supplied case-study PPT.

PPT delivery requirements:
- Maintain delivery person
- Assign delivery person
- Display delivery details
- Track status

## Run
1. Import this Maven project into Eclipse.
2. Use Java 17.
3. Run `FoodDeliveryApplication.java` as Spring Boot App.
4. Base URL: http://localhost:8080

## Postman flow
1. POST /api/delivery/persons
2. POST /api/delivery/orders
3. PUT /api/delivery/orders/1001/assign/1
4. GET /api/delivery/orders/1001
5. PUT /api/delivery/orders/1001/status
6. GET /api/delivery/persons

See the main answer for exact JSON bodies.
