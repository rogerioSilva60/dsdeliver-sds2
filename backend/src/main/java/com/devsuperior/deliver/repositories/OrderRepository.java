package com.devsuperior.deliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.deliver.entities.Order;
import com.devsuperior.deliver.entities.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("select distinct o from Order o join fetch o.products where o.status= ?1 order by o.moment asc")
	List<Order> findOrdersWithProducts(OrderStatus status);
}
