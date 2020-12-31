package com.myshop.demo.dao;

import com.myshop.demo.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:initProducts.sql")})
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void checkFindByNameAfterSql(){
        //execute
        Product actualProduct = productRepository.findFirstByTitle("Kulebyaka");

        //check
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(555, actualProduct.getId());
        Assertions.assertEquals("Kulebyaka", actualProduct.getTitle());
        Assertions.assertEquals(100.0, actualProduct.getPrice());
    }
}