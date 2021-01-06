package com.devsuperior.deliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.deliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
