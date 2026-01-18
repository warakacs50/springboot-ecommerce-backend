package com.kavinda.aicom.model;

import jakarta.persistence.*;
import com.kavinda.aicom.model.User;
import java.util.List;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private BigDecimal price;
    private String category;
    private long stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Product(){}

    public Product( String name , BigDecimal price , String category ,long stock   ){

        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;


    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getStock() {
        return stock;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

}
