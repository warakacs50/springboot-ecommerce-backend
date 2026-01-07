package com.kavinda.aicom.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Many CartItems can reference the same Product
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // Many CartItems belong to one Cart
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Quantity of the product in this cart item
    private int quantity;

    // Price snapshot at the time item was added to cart
    private BigDecimal priceAtTime;

    // ==================== Constructors ====================
    public CartItem() {
    }

    public CartItem(Product product, Cart cart, Integer quantity, BigDecimal priceAtTime) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    // ==================== Getters & Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(BigDecimal priceAtTime) {
        this.priceAtTime = priceAtTime;
    }
}
