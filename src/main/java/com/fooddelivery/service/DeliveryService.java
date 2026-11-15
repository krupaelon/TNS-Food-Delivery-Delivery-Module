package com.fooddelivery.service;

import com.fooddelivery.model.DeliveryPerson;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderStatus;
import com.fooddelivery.repository.DeliveryPersonRepository;
import com.fooddelivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryPersonRepository deliveryPersonRepository;
    private final OrderRepository orderRepository;

    public DeliveryService(DeliveryPersonRepository deliveryPersonRepository,
                           OrderRepository orderRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
        this.orderRepository = orderRepository;
    }

    public DeliveryPerson addDeliveryPerson(DeliveryPerson person) {
        return deliveryPersonRepository.save(person);
    }

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    public DeliveryPerson getDeliveryPerson(Long id) {
        return deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery person not found: " + id));
    }

    public DeliveryPerson updateDeliveryPerson(Long id, DeliveryPerson person) {
        DeliveryPerson existing = getDeliveryPerson(id);
        existing.setName(person.getName());
        existing.setContactNo(person.getContactNo());
        return deliveryPersonRepository.save(existing);
    }

    public void deleteDeliveryPerson(Long id) {
        if (!deliveryPersonRepository.existsById(id)) {
            throw new RuntimeException("Delivery person not found: " + id);
        }
        deliveryPersonRepository.deleteById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order assignDeliveryPerson(Long orderId, Long deliveryPersonId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        getDeliveryPerson(deliveryPersonId);

        order.setDeliveryPersonId(deliveryPersonId);
        order.setStatus(OrderStatus.ASSIGNED);
        return orderRepository.save(order);
    }

    public Order updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
