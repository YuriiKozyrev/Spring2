package com.myshop.demo.service;

import com.myshop.demo.dao.ProductRepository;
import com.myshop.demo.domain.Product;
import com.myshop.demo.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.Optional;

class ProductServiceImplTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserService userService;
    @Mock
    private BucketService bucketService;
    @Mock
    private SimpMessagingTemplate template;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository, userService, bucketService, template);
    }

    @Test
    void getById() {
        //have
        Product product = Product.builder()
                .id(10L)
                .title("Product")
                .price(10.5)
                .build();

        Mockito.when(productRepository.findById(Mockito.eq(10L))).thenReturn(Optional.of(product));
        //execute

        ProductDto productDto = productService.getById(10L);

        //check
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(product.getId(), productDto.getId());
        Assertions.assertEquals(product.getTitle(), productDto.getTitle());
        Assertions.assertEquals(product.getPrice(), productDto.getPrice());

    }
}