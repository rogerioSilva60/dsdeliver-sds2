package com.devsuperior.deliver.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.deliver.dto.ProductDTO;
import com.devsuperior.deliver.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	private ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
}
