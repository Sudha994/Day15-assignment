package com.example.ecommerce.controller;


import com.example.ecommerce.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDTO getProduct(@PathVariable("id") String id);

    @PutMapping("/products/{id}/reduceStock")
    ProductDTO reduceStock(@PathVariable("id") String id, @RequestParam("qty") int qty);
}