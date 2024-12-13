package ru.itis.service;

import ru.itis.model.Order;
import ru.itis.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService() {
        orderRepository = new OrderRepository();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

}
