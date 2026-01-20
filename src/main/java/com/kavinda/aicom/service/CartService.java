package com.kavinda.aicom.service;

import com.kavinda.aicom.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.kavinda.aicom.model.*;

import java.math.BigDecimal;

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
    public Cart addItemToCart(Integer userId, Integer productId, Integer quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity should be at least one");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));

        BigDecimal priceAtTime = product.getPrice();

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user)));

        CartItem cartItem = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem(product, cart, quantity, priceAtTime);
            cart.getItems().add(cartItem); // cascade handles saving
        }

        return cartRepository.save(cart);
    }



    // Remove from cart

    @Transactional
    public void removeitemFromCart(Integer userId , Integer productId){

       User user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("user not found "));

       Cart cart = cartRepository.findByUser(user)
               .orElseThrow(() -> new RuntimeException("cart not found"));

       CartItem cartItem = cart.getItems().stream()
               .filter(cartItem1 -> cartItem1.getProduct().getId().equals(productId))
               .findFirst()
               .orElseThrow(() -> new RuntimeException("product is not in the cart"));

       cart.getItems().remove(cartItem);
       cartItemRepository.delete(cartItem);



    }

    // clear cart
    @Transactional
    public void clearCart(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("cart not found  "));

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
    }

    @Transactional
    public Cart getCartByUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user)));
    }








}