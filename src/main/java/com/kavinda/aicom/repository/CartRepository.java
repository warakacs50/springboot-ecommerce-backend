package com.kavinda.aicom.repository;

import com.kavinda.aicom.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
@Repository
public interface CartRepository
        extends JpaRepository<Cart , Integer> {

    @EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Cart> findByUser(User user);
}