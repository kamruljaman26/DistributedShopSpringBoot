package com.shop.discovery.controller;

import com.shop.discovery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final String CUSTOMER_SERVICE_URL = "http://customer-service/api/customer";
    private final String ORDER_SERVICE_URL = "http://order-service/api/order";
    private final String PRODUCT_SERVICE_URL = "http://order-service/api/product";
    private final String ARTICLE_SERVICE_URL = "http://article-service/api/article";
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ShopController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Customer Section
     */
    // Get All Customers
    @GetMapping("/customers")
    public Mono<List<CustomerResponse>> getAllCustomers() {
        return webClientBuilder.build().get()
                .uri(CUSTOMER_SERVICE_URL)
                .retrieve()
                .bodyToFlux(CustomerResponse.class)
                .collectList();
    }

    // Create Customer
    @PostMapping("/customers")
    public Mono<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return webClientBuilder.build().post()
                .uri(CUSTOMER_SERVICE_URL)
                .body(Mono.just(request), CustomerRequest.class)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    // Get Customer by ID
    @GetMapping("/customers/{id}")
    public Mono<CustomerResponse> getCustomer(@PathVariable Long id) {
        return webClientBuilder.build().get()
                .uri(CUSTOMER_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    // Update Customer
    @PutMapping("/customers/{id}")
    public Mono<Void> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return webClientBuilder.build().put()
                .uri(CUSTOMER_SERVICE_URL + "/" + id)
                .body(Mono.just(request), CustomerRequest.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // Delete Customer
    @DeleteMapping("/customers/{id}")
    public Mono<Void> deleteCustomer(@PathVariable Long id) {
        return webClientBuilder.build().delete()
                .uri(CUSTOMER_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    /**
     * Order and Products
     */
    // Create Product
    @PostMapping("/products")
    public Mono<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return webClientBuilder.build().post()
                .uri(PRODUCT_SERVICE_URL)
//                .body(Mono.just(request), ProductRequest.class)
                .retrieve()
                .bodyToMono(ProductResponse.class);
    }

    // Get All Products
    @GetMapping("/products")
    public Mono<List<ProductResponse>> getAllProducts() {
        return webClientBuilder.build().get()
                .uri(PRODUCT_SERVICE_URL)
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList();
    }

    // Get Single Product by ID
    @GetMapping("/products/{id}")
    public Mono<ProductResponse> getProduct(@PathVariable Long id) {
        return webClientBuilder.build().get()
                .uri(PRODUCT_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(ProductResponse.class);
    }

    // Update Product
    @PutMapping("/products/{id}")
    public Mono<Void> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return webClientBuilder.build().put()
                .uri(PRODUCT_SERVICE_URL + "/" + id)
//                .body(Mono.just(request), ProductRequest.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // Delete Product
    @DeleteMapping("/products/{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id) {
        return webClientBuilder.build().delete()
                .uri(PRODUCT_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // Get All Orders
// Order Endpoints
    @PostMapping("/orders")
    public Mono<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return webClientBuilder.build().post()
                .uri(ORDER_SERVICE_URL)
                .body(Mono.just(request), OrderRequest.class)
                .retrieve()
                .bodyToMono(OrderResponse.class);
    }

    @GetMapping("/orders")
    public Mono<List<OrderResponse>> getAllOrders() {
        return webClientBuilder.build().get()
                .uri(ORDER_SERVICE_URL)
                .retrieve()
                .bodyToFlux(OrderResponse.class)
                .collectList();
    }

    @GetMapping("/orders/{id}")
    public Mono<OrderResponse> getOrder(@PathVariable Long id) {
        return webClientBuilder.build().get()
                .uri(ORDER_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(OrderResponse.class);
    }

    @PutMapping("/orders/{id}")
    public Mono<Void> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        return webClientBuilder.build().put()
                .uri(ORDER_SERVICE_URL + "/" + id)
                .body(Mono.just(request), OrderRequest.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @DeleteMapping("/orders/{id}")
    public Mono<Void> deleteOrder(@PathVariable Long id) {
        return webClientBuilder.build().delete()
                .uri(ORDER_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    /**
     * Article Section
     */

    // Get All Articles
    @GetMapping("/articles")
    public Mono<List<ArticleResponse>> getAllArticles() {
        return webClientBuilder.build().get()
                .uri(ARTICLE_SERVICE_URL)
                .retrieve()
                .bodyToFlux(ArticleResponse.class)
                .collectList();
    }

    // Create Article
    @PostMapping("/articles")
    public Mono<ArticleResponse> createArticle(@RequestBody ArticleRequest request) {
        return webClientBuilder.build().post()
                .uri(ARTICLE_SERVICE_URL)
                .body(Mono.just(request), ArticleRequest.class)
                .retrieve()
                .bodyToMono(ArticleResponse.class);
    }

    // Get Single Article by ID
    @GetMapping("/articles/{id}")
    public Mono<ArticleResponse> getArticle(@PathVariable Long id) {
        return webClientBuilder.build().get()
                .uri(ARTICLE_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(ArticleResponse.class);
    }

    // Update Article
    @PutMapping("/articles/{id}")
    public Mono<Void> updateArticle(@PathVariable Long id, @RequestBody ArticleRequest request) {
        return webClientBuilder.build().put()
                .uri(ARTICLE_SERVICE_URL + "/" + id)
                .body(Mono.just(request), ArticleRequest.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // Delete Article
    @DeleteMapping("/articles/{id}")
    public Mono<Void> deleteArticle(@PathVariable Long id) {
        return webClientBuilder.build().delete()
                .uri(ARTICLE_SERVICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
