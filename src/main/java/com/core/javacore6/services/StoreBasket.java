package com.core.javacore6.services;

import com.core.javacore6.models.Basket;

import java.util.List;
import java.util.Set;

public interface StoreBasket {
    Set<Integer> getBasket();

    List<Integer> addToBasket(List<Integer> IDs);
}
