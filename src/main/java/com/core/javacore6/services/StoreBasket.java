package com.core.javacore6.services;

import com.core.javacore6.models.Basket;

public interface StoreBasket {
    Basket getBasket();

    Integer addToBasket(Integer ID);
}
