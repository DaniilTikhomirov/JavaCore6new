package com.core.javacore6.models;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;



@Component
@SessionScope
public class Basket {
    private final Set<Integer> basket;

    public Basket() {
        this.basket = new HashSet<>();
    }

    public void addProduct(Integer ID) {
        basket.add(ID);
    }

    @PostConstruct
    public void setup(){
        System.out.println("hello");
    }

    public Set<Integer> getBasket() {
        return this.basket;
    }

}
