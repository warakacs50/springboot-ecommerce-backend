package com.kavinda.aicom.repository;

import com.kavinda.aicom.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository
        extends JpaRepository<CartItem , Integer> {
}