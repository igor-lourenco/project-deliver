package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}