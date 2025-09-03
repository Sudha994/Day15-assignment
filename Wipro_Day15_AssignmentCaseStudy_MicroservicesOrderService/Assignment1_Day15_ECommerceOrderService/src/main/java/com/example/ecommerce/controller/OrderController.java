package com.example.ecommerce.controller;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private PaymentClient paymentClient;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        ProductDTO product = productClient.getProduct(order.getProductId());
        if (product == null || product.getStock() < order.getQuantity()) {
            order.setStatus("FAILED");
            return order;
        }

        double totalPrice = product.getPrice() * order.getQuantity();
        order.setId(UUID.randomUUID().toString());
        order.setTotalAmount(totalPrice);
        order.setStatus("PENDING_PAYMENT");
        orderRepo.save(order);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(order.getId());
        paymentRequest.setAmount(totalPrice);
        paymentRequest.setPaymentMethod("Credit Card");

        PaymentResponse paymentResponse = paymentClient.makePayment(paymentRequest);
        if ("SUCCESS".equals(paymentResponse.getStatus())) {
            order.setStatus("CONFIRMED");
            productClient.reduceStock(order.getProductId(), order.getQuantity());
        }
        orderRepo.save(order);
        return order;
    }
    
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}