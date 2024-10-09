package com.core.javacore6.services;

import com.core.javacore6.models.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreBasketImpl implements StoreBasket{
    private final Basket basket;

    @Autowired
    public StoreBasketImpl(Basket basket){
        this.basket = basket;
    }

    @Override
    public Basket getBasket() {
        return basket;
    }

    @Override
    public Integer addToBasket(Integer ID){
        basket.addProduct(ID);
        return ID;
    }
}
