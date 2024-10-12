package kz.runtime.storespringproject.service;

import kz.runtime.storespringproject.entities.Basket;
import kz.runtime.storespringproject.repos.BasketRepository;
import kz.runtime.storespringproject.repos.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public void save(Basket basket) {
        basketRepository.save(basket);

    }


    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

    public Basket findBasketById(Long id) {
        return basketRepository.findById(id).orElseThrow();
    }

    public List<Basket> findBasketByProductId(Long productId) {
        return basketRepository.findBasketByProductId(productId);
    }

    public Basket incrementQuantityOfProductsInBasket(Long productId) {
        List<Basket> basket = findBasketByProductId(productId);
        for (Basket b : basket) {
            b.setQuantity(b.getQuantity() + 1);
            basketRepository.save(b);

            return b;
        }
        return null;
    }

    public Basket decrementQuantityOfProductsInBasket(Long productId) {
        List<Basket> basket = findBasketByProductId(productId);
        for (Basket b : basket) {
            if (b.getQuantity() > 0) {
                b.setQuantity(b.getQuantity() - 1);
                basketRepository.save(b);
            }
            if (b.getQuantity() == 0) {
                basketRepository.delete(b);
            }
            return b;

        }
        return null;
    }


}
