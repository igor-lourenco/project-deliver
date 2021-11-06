package com.delivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.dto.OrderDTO;
import com.delivery.entities.Order;
import com.delivery.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true) //só leitura no banco
	public List<OrderDTO> findAllPaged(){
		List<Order> entity = repository.findOrdersWithProducts();
		return entity.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
