package com.milenyumsoft.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
public class HelloWorldController {



    @GetMapping("holaseg")
    @PreAuthorize(" hasRole('ADMIN') ")
    public String secHelloWorld(){
        return "Hola Mundo Mylenyum-Soft con seguridad";
    }


    @GetMapping("/holanoseg")
    @PreAuthorize("permitAll()")
    public String noSecHelloWorld(){
        return "Hola mundo Mylenuum-Soft sin seguridad" ;
    }
}
