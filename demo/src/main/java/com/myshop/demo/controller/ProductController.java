package com.myshop.demo.controller;

import com.myshop.demo.dto.ProductDto;
import com.myshop.demo.service.ProductService;
import com.myshop.demo.service.SessionObjectHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {



    private final ProductService productService;
    private final SessionObjectHolder sessionObjectHolder;

    public ProductController(ProductService productService, SessionObjectHolder sessionObjectHolder) {
        this.productService = productService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
//    @MeasureMethod
    public String list(Model model){
//        sessionObjectHolder.addClick();
        List<ProductDto> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/{id}/bucket")
    public ResponseEntity<Void> addBucket(@PathVariable Long id, Principal principal){
//    public String addBucket(@PathVariable Long id, Principal principal){
//        sessionObjectHolder.addClick();
        if(principal == null){
            return null;
        }
        productService.addToUserBucket(id, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
//        return "redirect:/products";
    }



    @PostMapping
    public ResponseEntity<Void> addProduct(ProductDto dto){
        productService.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/products")
    public void messageAddProduct(ProductDto dto){
        productService.addProduct(dto);
    }

}