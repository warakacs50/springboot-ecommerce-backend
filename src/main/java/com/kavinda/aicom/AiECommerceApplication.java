package com.kavinda.aicom;

import com.kavinda.aicom.model.*;
import com.kavinda.aicom.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootApplication
public class AiECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiECommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner testDatabase(
            UserRepository userRepo,
            CartRepository cartRepo,
            ProductRepository productRepo,
            CartItemRepository cartItemRepo
    ) {
        return args -> {
            System.out.println("=== Testing Model & Repository Layer ===");

            // ====== 1️⃣ Create a User ======
            User seller = new User("SellerUser", "seller@example.com", Role.SELLER);
            User customer = new User("Mihira", "mihira@example.com", Role.CUSTOMER);
            userRepo.save(seller);
            userRepo.save(customer);

            // ====== 2️⃣ Create Carts for Users ======
            Cart customerCart = new Cart(customer);
            cartRepo.save(customerCart);

            // ====== 3️⃣ Create Products ======
            Product product1 = new Product(null, "Laptop", new BigDecimal("1500"), "Electronics", 10, seller);
            Product product2 = new Product(null, "Phone", new BigDecimal("800"), "Electronics", 20, seller);
            productRepo.save(product1);
            productRepo.save(product2);

            // ====== 4️⃣ Add CartItems ======
            CartItem item1 = new CartItem(product1, customerCart, 1, product1.getPrice());
            CartItem item2 = new CartItem(product2, customerCart, 2, product2.getPrice());
            cartItemRepo.save(item1);
            cartItemRepo.save(item2);

            // ====== 5️⃣ Fetch Cart and Display Items ======
            Optional<Cart> savedCartOpt = cartRepo.findById(customerCart.getId());
            if (savedCartOpt.isPresent()) {
                Cart savedCart = savedCartOpt.get();
                System.out.println("Cart for user: " + savedCart.getUser().getName());
                System.out.println("Items in cart:");
                savedCart.getItems().forEach(ci -> {
                    System.out.println("- " + ci.getProduct().getName()
                            + " | Quantity: " + ci.getQuantity()
                            + " | Price: " + ci.getPriceAtTime());
                });
            }

            System.out.println("=== Model & Repository Test Complete ===");
        };
    }
}
