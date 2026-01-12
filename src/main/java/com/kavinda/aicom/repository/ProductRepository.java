package com.kavinda.aicom.repository;

import com.kavinda.aicom.model.Cart;
import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
extends JpaRepository<Product , Integer> {

    Optional<Product> findByUser(User user);
    Optional<Product> findById(Product id);
}