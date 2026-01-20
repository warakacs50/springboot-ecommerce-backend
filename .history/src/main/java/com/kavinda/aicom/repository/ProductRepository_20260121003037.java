package com.kavinda.aicom.repository;

import com.kavinda.aicom.model.Cart;
import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.model.*;
import org.hibernate.metamodel.mapping.internal.SimpleAttributeMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository
extends JpaRepository<Product , Integer> {

    List<Product> findByUserId(Integer userId);
    Optional<Product> findById(Product id);
    
}