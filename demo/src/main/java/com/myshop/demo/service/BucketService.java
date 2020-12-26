package com.myshop.demo.service;

import com.myshop.demo.domain.Bucket;
import com.myshop.demo.domain.User;
import com.myshop.demo.dto.BucketDto;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);
    BucketDto getBucketByUser(String name);
    void commitBucketToOrder(String username);
}
