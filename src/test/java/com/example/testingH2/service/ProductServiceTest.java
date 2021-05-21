package com.example.testingH2.service;

import com.example.testingH2.entity.Product;

public class ProductServiceTest {
    public static Product createProduct(){
        Product product=new Product();
        product.setProductName("table");
        product.setDescription("just a table");
        product.setImageSource("http://tables.com/images/table1.png");
        product.setPrice(150);
        product.setProductType("furniture");
        product.setStock(1);
        return product;
    }
}
