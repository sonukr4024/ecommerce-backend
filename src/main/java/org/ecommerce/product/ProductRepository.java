package org.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndCategory(String name, Category category);

    List<Product> findByCategoryId(long categoryId);
}
