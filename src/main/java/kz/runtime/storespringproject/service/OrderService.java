package kz.runtime.storespringproject.service;

import kz.runtime.storespringproject.entities.OrderStatus;
import kz.runtime.storespringproject.entities.Orders;
import kz.runtime.storespringproject.repos.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    public void save(Orders orders) {
        ordersRepository.save(orders);
    }
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Orders findById(long id) {
        return ordersRepository.findById(id).orElseThrow();
    }

    public void updateOrderStatus(Long id, Orders order) {
        Orders orders = ordersRepository
                .findById(id)
                .orElseThrow();

        orders.setStatus(order.getStatus());
        ordersRepository.save(orders);
    }
}
