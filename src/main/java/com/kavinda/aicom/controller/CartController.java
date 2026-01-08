package com.kavinda.aicom.controller;

import com.kavinda.aicom.service.*;
import com.kavinda.aicom.model.Cart;
import com.kavinda.aicom.repository.CartRepository;
import com.kavinda.aicom.model.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    //constructor injection
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    //add to cart

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(

        @RequestParam Integer userId ,
        @RequestParam Integer productId,
        @RequestParam  Integer quantity
    ) {
        Cart cart = cartService.addItemToCart(userId,productId,quantity);
        return ResponseEntity.ok(cart);
    }

    // update item of the cart

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeItem(
        @RequestParam Integer userId,
        @RequestParam Integer  productId
    ) {
        cartService.removeitemFromCart(userId,productId);
        return ResponseEntity.noContent().build();
    }

    //clear cart
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(
            @RequestParam Integer userId
    ) {
        cartService.clearCart(userId);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam Integer userId) {
        Cart cart = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cart);
    }








}