package com.kavinda.aicom.repository;

import com.kavinda.aicom.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {


    Optional<Seller> findByUserId(Integer userId);
}
