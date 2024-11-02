package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Orders;
import kz.runtime.storespringproject.repos.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void save(Orders orders) {
        ordersRepository.save(orders);
    }
}
