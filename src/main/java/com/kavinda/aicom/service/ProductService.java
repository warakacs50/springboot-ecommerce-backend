package com.kavinda.aicom.service;

import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.model.Role;
import com.kavinda.aicom.model.*;
import com.kavinda.aicom.repository.ProductRepository;
import com.kavinda.aicom.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // CREATE PRODUCT (only seller)
    public Product addProduct(Integer userId, Product product) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.SELLER) {
            throw new RuntimeException("Only sellers can create products");
        }

        // validate product
        if (product.getName() == null || product.getName().isBlank()) {
            throw new RuntimeException("Product name is required");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Product price must be greater than 0");
        }
        if (product.getStock() < 0) {
            product.setStock(0);
        }

        // attach seller (user) to product
        product.setUser(user);

        return productRepository.save(product);
    }

    // UPDATE PRODUCT (only owner)
    public Product updateProduct(Integer userId, Integer productId, Product product) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // authorization check
        if (!productFromDb.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Only the owner can update this product");
        }

        // update safely
        if (product.getName() != null && !product.getName().isBlank()) {
            productFromDb.setName(product.getName());
        }
        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            productFromDb.setPrice(product.getPrice());
        }
        if (product.getStock() >= 0) {
            productFromDb.setStock(product.getStock());
        }
        if (product.getCategory() != null && !product.getCategory().isBlank()) {
            productFromDb.setCategory(product.getCategory());
        }

        return productRepository.save(productFromDb);
    }

    // DELETE PRODUCT (only owner)
    public void removeProduct(Integer userId, Integer productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Only the owner can delete this product");
        }

        productRepository.delete(product);
    }

    // FETCH ALL PRODUCTS (for customers)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // FETCH PRODUCT BY ID
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProductsBySeller(Integer sellerId) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        return productRepository.findByUserId(sellerId);  // pass User object, not ID
    }

}
