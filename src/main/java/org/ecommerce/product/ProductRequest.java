package org.ecommerce.product;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private double price;
    private String image;
    private int qty;
    private String description;
    private String categoryName;
}
