package com.core.javacore6.controllers;


import com.core.javacore6.models.Basket;
import com.core.javacore6.services.StoreBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("store/order")
public class BasketController {
    private final StoreBasket storeBasket;

    @Autowired
    public BasketController(StoreBasket storeBasket) {
        this.storeBasket = storeBasket;
    }

    @GetMapping("/get")
    public Set<Integer> getBasket() {
        return storeBasket.getBasket();
    }

    @GetMapping("/add")
    public List<Integer> addToBasket(@RequestParam("ID") String ID) {
        String[] Ids = ID.split(",");

        return storeBasket.addToBasket(Arrays.stream(Ids).map(Integer::parseInt).toList());
    }
}
