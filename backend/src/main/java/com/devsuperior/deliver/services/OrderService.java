package com.devsuperior.deliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.deliver.dto.OrderDTO;
import com.devsuperior.deliver.dto.ProductDTO;
import com.devsuperior.deliver.entities.Order;
import com.devsuperior.deliver.entities.OrderStatus;
import com.devsuperior.deliver.entities.Product;
import com.devsuperior.deliver.repositories.OrderRepository;

@Service
public class OrderService {

	private OrderRepository repository;
	private ProductService productService;
	
	public OrderService(OrderRepository repository, ProductService productService) {
		this.repository = repository;
		this.productService = productService;
	}

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts(OrderStatus.PENDING);
		return list.stream()
					.map(o -> new OrderDTO(o))
					.collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO save(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {
			Product product = productService.getOne(p.getId());
			order.getProducts().add(product);
		}
		Order orderSaved = repository.save(order);
		return new OrderDTO(orderSaved);
	}
	
	@Transactional
	public OrderDTO setDevilered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		Order orderUpdated = repository.save(order);
		return new OrderDTO(orderUpdated);
	}
}
