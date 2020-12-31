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
import java.util.List;
import java.util.Objects;
import java.util.Optional;


class BucketServiceImplTest {

    private BucketService bucketService;

    @Mock
    private BucketRepository bucketRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private OrderService orderService;

    private BucketDetailDto bucketDetailDto;

    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bucketService = new BucketServiceImpl(bucketRepository, productRepository, userService, orderService);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void getBucketByUser() {

        //have
        String userName = "testUser";

        Product product = Product.builder()
                .id(10L)
                .title("Product")
                .price(10.5)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Bucket bucket = Bucket.builder()
                .user(user)
                .id(1L)
                .products(productList)
                .build();

        User user = User.builder()
                .id(100L)
                .name(userName)
                .email("123@test.com")
                .password("123")
                .role(Role.MANAGER)
                .bucket(bucket)
                .build();

        Mockito.when(userRepository.findById(Mockito.eq(100L))).thenReturn(Optional.of(user));


        //execute
        BucketDto bucketDto = bucketService.getBucketByUser(userName);

        // почему bucketDto создается, но внутри всё null?
        System.out.println(bucketDto);
//        System.out.println(user.getBucket().getProducts());
    }
}