package com.delivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.dto.ProductDTO;
import com.delivery.entities.Product;
import com.delivery.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true) //só leitura no banco
	public List<ProductDTO> findAllPaged(){
		List<Product> entity = repository.findAllByOrderByNameAsc();
		return entity.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
}
