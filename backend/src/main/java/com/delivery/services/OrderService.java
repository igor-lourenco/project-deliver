package com.delivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.dto.OrderDTO;
import com.delivery.dto.ProductDTO;
import com.delivery.entities.Order;
import com.delivery.entities.Product;
import com.delivery.entities.enums.OrderStatus;
import com.delivery.repositories.OrderRepository;
import com.delivery.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true) // só leitura no banco
	public List<OrderDTO> findAllPaged() {
		List<Order> entity = repository.findOrdersWithProducts();
		return entity.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order entity = repository.getOne(id);
		entity.setStatus(OrderStatus.DELIVERED);
		entity = repository.save(entity);
		return new OrderDTO(entity);
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order entity = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
				OrderStatus.PENDING);

		for (ProductDTO productDTO : dto.getProducts()) {
			Product product = productRepository.getOne(productDTO.getId());
			entity.getProducts().add(product);
		}

		entity = repository.save(entity);
		return new OrderDTO(entity);
	}
}
