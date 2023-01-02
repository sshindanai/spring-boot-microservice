package com.sshindanai.productservice.service;

import com.sshindanai.productservice.dto.ProductRequest;
import com.sshindanai.productservice.dto.ProductResponse;
import com.sshindanai.productservice.model.Product;
import com.sshindanai.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product created: {}", product.getId());
    }

    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();

        // same with => product -> mapToProductResponse(product)
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
