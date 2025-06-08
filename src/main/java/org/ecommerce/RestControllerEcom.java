package org.ecommerce;

import org.ecommerce.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RestControllerEcom {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // Add or Return existing category
    @PostMapping("/categories")
    public Category addCategory(@RequestParam String name) {
        return categoryService.saveCategory(name);
    }

    // Get all categories
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .image(productRequest.getImage())
                .qty(productRequest.getQty())
                .build();

        Product savedProduct = productService.saveProduct(product, productRequest.getCategoryName());

        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/products/{categoryId}")
    public List<Product> getProducts(@PathVariable long categoryId) {
        return productService.getProductsById(categoryId);
    }

    @GetMapping("/allproducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
