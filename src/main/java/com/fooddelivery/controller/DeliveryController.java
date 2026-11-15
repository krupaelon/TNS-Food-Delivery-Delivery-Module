package com.fooddelivery.controller;

import com.fooddelivery.model.DeliveryPerson;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderStatus;
import com.fooddelivery.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    // Maintain delivery person
    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryPerson addPerson(@Valid @RequestBody DeliveryPerson person) {
        return service.addDeliveryPerson(person);
    }

    @GetMapping("/persons")
    public List<DeliveryPerson> getPersons() {
        return service.getAllDeliveryPersons();
    }

    @GetMapping("/persons/{id}")
    public DeliveryPerson getPerson(@PathVariable Long id) {
        return service.getDeliveryPerson(id);
    }

    @PutMapping("/persons/{id}")
    public DeliveryPerson updatePerson(@PathVariable Long id,
                                       @Valid @RequestBody DeliveryPerson person) {
        return service.updateDeliveryPerson(id, person);
    }

    @DeleteMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        service.deleteDeliveryPerson(id);
    }

    // Minimal order endpoints needed to test the delivery module
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    // Assign delivery person to an order
    @PutMapping("/orders/{orderId}/assign/{deliveryPersonId}")
    public Order assign(@PathVariable Long orderId,
                        @PathVariable Long deliveryPersonId) {
        return service.assignDeliveryPerson(orderId, deliveryPersonId);
    }

    // Track/update delivery status
    @PutMapping("/orders/{orderId}/status")
    public Order updateStatus(@PathVariable Long orderId,
                              @RequestBody Map<String, String> body) {
        OrderStatus status = OrderStatus.valueOf(
                body.get("status").toUpperCase()
        );
        return service.updateStatus(orderId, status);
    }

    // Display delivery/order details
    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return service.getOrder(orderId);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return service.getAllOrders();
    }
}
