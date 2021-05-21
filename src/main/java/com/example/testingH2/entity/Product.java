package com.example.testingH2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {
    @Id
    int ProductId;
    String ProductName;
    String productType;
    String description;
    int stock;
    int price;
    String imageSource;
}
