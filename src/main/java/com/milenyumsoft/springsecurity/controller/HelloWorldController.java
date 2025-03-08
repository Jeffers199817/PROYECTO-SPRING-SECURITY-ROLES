package com.milenyumsoft.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {



    @GetMapping("holaseg")
    public String secHelloWorld(){
        return "Hola Mundo Mylenyum-Soft con seguridad";
    }


    @GetMapping("/holanoseg")
    public String noSecHelloWorld(){
        return "Hola mundo Mylenuum-Soft sin seguridad" ;
    }
}
