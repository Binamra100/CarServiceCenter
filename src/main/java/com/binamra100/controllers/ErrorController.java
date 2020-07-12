package com.binamra100.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException(
                "Expected: controller used to showcase what " + "happens when an exception is thrown");
    }
}
