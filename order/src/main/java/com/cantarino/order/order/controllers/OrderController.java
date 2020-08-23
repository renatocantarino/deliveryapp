package com.cantarino.order.order.controllers;


import com.cantarino.order.order.exception.ResourceNotFoundException;
import com.cantarino.order.order.models.Order;
import com.cantarino.order.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderRepository orderRepository;
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity< Order > getOrdersById(@PathVariable(value = "id") Long orderId)
            throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found :: " + orderId));
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/order")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity < Order > updateOrder(@PathVariable(value = "id") Long orderId,
                                                @Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        order.setCustomer_id(orderDetails.getCustomer_id());
        order.setDescription(orderDetails.getDescription());
        order.setTotal(orderDetails.getTotal());
        final Order updateCustomer = orderRepository.save(order);
        return ResponseEntity.ok(updateCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public Map< String, Boolean > deleteOrder(@PathVariable(value = "id") Long orderId)
            throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        orderRepository.delete(order);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
