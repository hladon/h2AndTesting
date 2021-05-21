package com.example.testingH2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericEntityRepository
        extends JpaRepository<Generic, Long> {
}