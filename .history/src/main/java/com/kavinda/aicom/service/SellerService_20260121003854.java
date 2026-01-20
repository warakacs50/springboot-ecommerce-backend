package com.kavinda.aicom.service;

import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.model.Seller;
import com.kavinda.aicom.model.User;
import com.kavinda.aicom.repository.ProductRepository;
import com.kavinda.aicom.repository.SellerRepository;
import com.kavinda.aicom.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.kavinda.aicom.model.Role;

import java.util.List;

@Service
@Transactional
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public SellerService(ProductRepository productRepository,SellerRepository sellerRepository, UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // CREATE seller profile
    public Seller createSeller(Integer userId, String storeName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != com.kavinda.aicom.model.Role.SELLER) {
            throw new RuntimeException("User role must be SELLER to create a seller profile");
        }

        if (sellerRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Seller profile already exists for this user");
        }

        Seller seller = new Seller(storeName, user);
        return sellerRepository.save(seller);
    }

    // GET seller by ID
    public Seller getSellerById(Integer id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }

    // GET all sellers
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    // UPDATE seller
    public Seller updateSeller(Integer id, String storeName) {
        Seller seller = getSellerById(id);
        seller.setStoreName(storeName);
        return sellerRepository.save(seller);
    }

    // DELETE seller
    public void deleteSeller(Integer id) {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }

    public List<Product> getMyProducts(Integer sellerUserId) {
        User seller = userRepository.findById(sellerUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (seller.getRole() != Role.SELLER) {
            throw new RuntimeException("Only sellers can view their products");
        }

        return productRepository.findByUserId(sellerUserId);
    }
}
