package com.devsuperior.deliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.deliver.dto.ProductDTO;
import com.devsuperior.deliver.entities.Product;
import com.devsuperior.deliver.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream()
					.map(p -> new ProductDTO(p))
					.collect(Collectors.toList());
	}
	
	public Product getOne(Long id) {
		return repository.getOne(id);
	}
}
