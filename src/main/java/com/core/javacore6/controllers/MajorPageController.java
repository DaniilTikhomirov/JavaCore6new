package com.core.javacore6.controllers;


import com.core.javacore6.services.MajorPageService;
import com.core.javacore6.services.MajorPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MajorPageController {
    private final MajorPageService majorPageService;

    @Autowired
    public MajorPageController(MajorPageService majorPageService) {
        this.majorPageService = majorPageService;
    }

    @GetMapping
    public String major() {
        return majorPageService.getAllInfo();
    }
}
