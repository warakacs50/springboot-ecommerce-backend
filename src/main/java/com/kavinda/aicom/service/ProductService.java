package com.kavinda.aicom.service;

import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.model.User;
import com.kavinda.aicom.repository.ProductRepository;
import com.kavinda.aicom.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository , UserRepository userRepository){
        this.productRepository = productRepository;
        this.userRepository = userRepository;

    }

    // add product

    @Transactional
    public Product addProduct(Integer sellerId , Product productId){


    }

}
