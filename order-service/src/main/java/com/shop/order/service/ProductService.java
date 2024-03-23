package com.shop.order.service;

import com.shop.order.dto.ProductRequest;
import com.shop.order.dto.ProductResponse;
import com.shop.order.model.Product;
import com.shop.order.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .skuCode(productRequest.getSkuCode())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepo.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return productList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .skuCode(product.getSkuCode())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToProductResponse(product);
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productRequest.getName());
        product.setSkuCode(productRequest.getSkuCode());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        productRepo.save(product);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }
}
