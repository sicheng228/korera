package com.itlize.Korera;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/Hello")
    public String sayHello() {
        return "test";
    }
}
