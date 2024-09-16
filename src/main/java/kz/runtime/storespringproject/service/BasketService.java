package kz.runtime.storespringproject.service;

import kz.runtime.storespringproject.entities.Basket;
import kz.runtime.storespringproject.repos.BasketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    public void save(Basket basket) {
        basketRepository.save(basket);
    }


    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

}
