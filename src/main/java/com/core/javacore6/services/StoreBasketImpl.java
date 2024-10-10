package com.core.javacore6.services;

import com.core.javacore6.models.Basket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Set;

@Service
public class StoreBasketImpl implements StoreBasket{
    private final Basket basket;

    @Autowired
    public StoreBasketImpl(Basket basket){
        this.basket = basket;
    }

    @Override
    public Set<Integer> getBasket() {
        return basket.getBasket();
    }

    @Override
    public List<Integer> addToBasket(List<Integer> IDs){
        for (Integer i : IDs){
            basket.addProduct(i);
        }
        return IDs;
    }


}
