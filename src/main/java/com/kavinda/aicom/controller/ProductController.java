package com.kavinda.aicom.controller;

import com.kavinda.aicom.model.Product;
import com.kavinda.aicom.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(
            @RequestParam Integer sellerId,
            @RequestBody Product product) {
        Product saveProduct = productService.addProduct(sellerId, product);
        return ResponseEntity.ok(saveProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @RequestParam Integer sellerId,
            @PathVariable Integer productId,
            @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(sellerId, productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @RequestParam Integer sellerId,
            @PathVariable Integer productId) {
        productService.removeProduct(sellerId, productId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/api/products")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer sellerId) {
        List<Product> products = (sellerId != null) ?
                productService.getProductsBySeller(sellerId) :
                productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


}
