package com.hansei.api.controller;

import com.hansei.api.dto.ApiResponse;
import com.hansei.api.dto.ProductResponseDto;
import com.hansei.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ApiResponse<List<ProductResponseDto>> findAll() {
        return ApiResponse.success(productService.findAll());
    }
}
