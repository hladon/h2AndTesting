package com.example.testingH2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Rating {
    @Id
    int ratingId;
    @ManyToOne
    Member  memberId;
    @ManyToOne
    Product productId;
    int value;
}
