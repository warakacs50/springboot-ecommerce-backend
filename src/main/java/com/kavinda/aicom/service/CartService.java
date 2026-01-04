package com.kavinda.aicom.service;

import com.kavinda.aicom.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.kavinda.aicom.model.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    // Dependency injection
    public CartService(CartRepository cartRepository, ProductRepository productRepository,
                       UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    //add item to cart
    @Transactional
    public Cart addItemToCart (Integer userId, Integer productId , Integer quantity){
        if(quantity < 0){
            throw new IllegalArgumentException("quantity should be at least one ");

        }

        //1.fetch user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        //2.fetch product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));

        //3.fetch cart or create new cart
        Cart cart = cartRepository.findById(user.getId())
                .orElseGet(() -> cartRepository.save(new Cart(user)));




    }



}