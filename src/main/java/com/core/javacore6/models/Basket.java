package com.core.javacore6.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;


@Repository
@Scope(scopeName = "prototype")
public class Basket {
    private final Set<Integer> basket;

    public Basket(Set<Integer> basket) {
        this.basket = new HashSet<Integer>(basket);
    }

    public void addProduct(Integer ID) {
        basket.add(ID);
    }

    public Set<Integer> getBasket() {
        return this.basket;
    }

}
