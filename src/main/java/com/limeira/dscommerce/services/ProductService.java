package com.limeira.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limeira.dscommerce.dto.ProductDTO;
import com.limeira.dscommerce.entities.Product;
import com.limeira.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);

		return dto;
		
	}
	
	
	// no Postman fazendo o get
	// localhost:8080/products?size=12&page=0&sort=name,desc
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		
		Page<Product> result = repository.findAll(pageable);

		return result.map(x -> new ProductDTO(x));
		
	}
	

	@Transactional
	public ProductDTO insert(ProductDTO dto) {

		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());		
	}


	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {

		Product entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}	
	
	
	@Transactional
	public void delete(Long id) {
		
		repository.deleteById(id);
		
	}	
	
}
