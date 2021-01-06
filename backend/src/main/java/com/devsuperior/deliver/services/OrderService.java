package com.devsuperior.deliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.deliver.dto.OrderDTO;
import com.devsuperior.deliver.entities.Order;
import com.devsuperior.deliver.entities.OrderStatus;
import com.devsuperior.deliver.repositories.OrderRepository;

@Service
public class OrderService {

	private OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts(OrderStatus.PENDING);
		return list.stream()
					.map(o -> new OrderDTO(o))
					.collect(Collectors.toList());
	}
}
