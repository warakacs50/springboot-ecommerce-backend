package com.kavinda.aicom.service;

import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.model.Role;
import com.kavinda.aicom.model.User;
import com.kavinda.aicom.repository.ProductRepository;
import com.kavinda.aicom.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


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
    public Product addProduct(Integer sellerId , Product product){

        //1.fetch user

        User user = userRepository.findById(sellerId)
                .orElseThrow(()->  new RuntimeException("invalid useer id "));

        // validate seller role

        if(user.getRole() != Role.SELLER ){
            throw new RuntimeException("user is not a seller");
        }

        //3.validate the product data(input data to the control product )

        if(product.getName() == null){
            throw new RuntimeException("product name requred");
        }
        if(product.getPrice() == null ){
            throw new RuntimeException("product price must be greater than 0 ");
        }

        // attach seller to the product
        product.setUser(user);
        return productRepository.save(product);





    }

    @Transactional
    public void removeProduct(Integer sellerId , Integer productId){

        //1. fetch user

        User user = userRepository.findById(sellerId)
                .orElseThrow(()->  new RuntimeException("invalid useer id "));

        //2. Fetch product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        //3.authrization check
        if(!product.getUser().getId().equals(user.getId())){
            throw new RuntimeException("only the owner can remove this product");

        }

        //4.delete product
        productRepository.delete(product);

    }

    @Transactional
    public  Product updateProduct(Integer sellerId, Integer productId) {

        //1. fetch user

        User user = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("invalid useer id "));

        //2. Fetch product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        //3.authrization check
        if (!product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("only the owner can remove this product");
        }

        //4.validate and update data
        if(product.getName() != null && product.getName().isBlank()){
            product.setName(product.getName());
        }

        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            product.setPrice(product.getPrice());
        }

        if (product.getStock() >= 0) {
            product.setStock(product.getStock());
        }

        if (product.getCategory() != null && !product.getCategory().isBlank()) {
            product.setCategory(product.getCategory());
        }

        // 5. Save changes
        return productRepository.save(product);






    }

}
