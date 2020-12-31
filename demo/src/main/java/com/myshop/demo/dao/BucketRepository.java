package com.myshop.demo.dao;

import com.myshop.demo.domain.Bucket;
import com.myshop.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    Bucket findFirstByUser_id(Long id);
}
