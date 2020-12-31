package com.myshop.demo.dao;

import com.myshop.demo.domain.Product;
import com.myshop.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByTitle(String title);
}
