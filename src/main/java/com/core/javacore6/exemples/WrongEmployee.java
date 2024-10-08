package com.core.javacore6.exemples;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongEmployee extends RuntimeException {
    public WrongEmployee(String message) {
        super(message);
    }
}
