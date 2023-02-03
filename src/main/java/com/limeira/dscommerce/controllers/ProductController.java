package com.limeira.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limeira.dscommerce.dto.ProductDTO;
import com.limeira.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		
		ProductDTO dto = service.findById(id);
		
		return dto;
		
	}
	
	// GET Products paged
	// no Postman fazendo o get
	// localhost:8080/products?size=12&page=0&sort=name,desc
	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable) {
		
		return service.findAll(pageable);
		
	}
	

	@PostMapping
	public ProductDTO insert(@RequestBody ProductDTO dto) {

		return service.insert(dto);
		
	}
	
	
	

}