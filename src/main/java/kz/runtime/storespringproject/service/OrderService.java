package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Orders;
import kz.runtime.storespringproject.repos.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void save(Orders orders) {
        ordersRepository.save(orders);
    }
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Orders findById(long id) {
        return ordersRepository.findById(id).orElseThrow();
    }

}
