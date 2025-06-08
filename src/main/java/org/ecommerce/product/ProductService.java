package org.ecommerce.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // ✅ Save or Update Product
    @Transactional
    public Product saveProduct(Product product, String categoryName) {

        // Check if category exists
        Category category = categoryRepo.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepo.save(newCategory);
                });

        // Check if product exists with same name and category
        Optional<Product> existingProductOpt = productRepo.findByNameAndCategory(product.getName(), category);

        if (existingProductOpt.isPresent()) {
            // Update quantity
            Product existingProduct = existingProductOpt.get();
            existingProduct.setQty(existingProduct.getQty() + product.getQty());
            return productRepo.save(existingProduct);
        } else {
            // New product
            product.setCategory(category);
            return productRepo.save(product);
        }
    }

    public List<Product> getProductsById(long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
