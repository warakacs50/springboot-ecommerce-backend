package com.kavinda.aicom.controller;

import com.kavinda.aicom.model.Cart;
import com.kavinda.aicom.repository.CartRepository;
import com.kavinda.aicom.model.CartItem;
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




}