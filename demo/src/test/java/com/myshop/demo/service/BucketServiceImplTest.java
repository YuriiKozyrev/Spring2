package com.myshop.demo.service;

import com.myshop.demo.dao.BucketRepository;
import com.myshop.demo.dao.ProductRepository;
import com.myshop.demo.dao.UserRepository;
import com.myshop.demo.domain.Bucket;
import com.myshop.demo.domain.Product;
import com.myshop.demo.domain.Role;
import com.myshop.demo.domain.User;
import com.myshop.demo.dto.BucketDetailDto;
import com.myshop.demo.dto.BucketDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;;
import java.util.Optional;


class BucketServiceImplTest {

    private BucketService bucketService;
    private UserService userService;

    @Mock
    private BucketRepository bucketRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private OrderService orderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
        bucketService = new BucketServiceImpl(bucketRepository, productRepository, userService, orderService);
    }

    @Test
    void getBucketByUser() {

        //have
        Product product = Product.builder()
                .id(10L)
                .title("Product")
                .price(10.5)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product);

        Bucket bucket = Bucket.builder()
                .id(1L)
                .products(productList)
                .build();

        User user = User.builder()
                .name("testuser")
                .id(1L)
                .role(Role.CLIENT)
                .bucket(bucket)
                .build();

        Mockito.when(bucketRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(bucket));
        Mockito.when(userRepository.findFirstByName(Mockito.eq("testuser"))).thenReturn(user);

        //execute
        BucketDto bucketDto = bucketService.getBucketByUser("testuser");

        //chek
        Assertions.assertNotNull(bucketDto);
        Assertions.assertNotNull(bucketDto.getBucketDetails());
        Assertions.assertEquals(product.getId(), bucketDto.getBucketDetails().get(0).getProductId());
        Assertions.assertEquals(product.getTitle(), bucketDto.getBucketDetails().get(0).getTitle());
        Assertions.assertEquals(productList.size(), bucketDto.getBucketDetails().get(0).getAmount());
    }
}