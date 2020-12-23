package com.myshop.demo.endpoint;

import com.myshop.demo.dto.ProductDto;
import com.myshop.demo.service.ProductService;
import com.myshop.demo.ws.products.GetProductsRequest;
import com.myshop.demo.ws.products.GetProductsResponse;
import com.myshop.demo.ws.products.ProductWS;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class ProductsEndpoint {

    public static final String NAMESPACE_URL = "http://myshop.com/demo/ws/products";

    private final ProductService productService;

    public ProductsEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getGreeting(@RequestPayload GetProductsRequest request)
            throws DatatypeConfigurationException {
        GetProductsResponse response = new GetProductsResponse();
        productService.getAll()
                .forEach(dto -> response.getProducts().add(createProductWS(dto)));
        return response;
    }

    private ProductWS createProductWS(ProductDto dto){
        ProductWS ws = new ProductWS();
        ws.setId(dto.getId());
        ws.setPrice(dto.getPrice());
        ws.setTitle(dto.getTitle());
        return ws;
    }
}
