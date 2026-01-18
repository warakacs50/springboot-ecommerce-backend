package com.kavinda.aicom.controller;

import com.kavinda.aicom.model.Seller;
import com.kavinda.aicom.service.SellerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // CREATE seller
    @PostMapping
    public ResponseEntity<Seller> createSeller(
            @RequestParam Integer userId,
            @RequestParam @Valid @NotBlank String storeName) {

        Seller seller = sellerService.createSeller(userId, storeName);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }

    // GET seller by ID
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable Integer id) {
        Seller seller = sellerService.getSellerById(id);
        return ResponseEntity.ok(seller);
    }

    // GET all sellers
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }

    // UPDATE seller
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(
            @PathVariable Integer id,
            @RequestParam @Valid @NotBlank String storeName) {

        Seller updatedSeller = sellerService.updateSeller(id, storeName);
        return ResponseEntity.ok(updatedSeller);
    }

    // DELETE seller
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Integer id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
