package com.devsuperior.deliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.deliver.dto.OrderDTO;
import com.devsuperior.deliver.services.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	private OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO dto) {
		OrderDTO orderSaved = service.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(orderSaved.getId()).toUri();
		return ResponseEntity.created(uri).body(orderSaved);
	}
}
