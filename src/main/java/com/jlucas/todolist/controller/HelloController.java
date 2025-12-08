package com.jlucas.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String primeiraMensagem(){
        return "Estou pronto pra guerra!";
    }
}
