package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
