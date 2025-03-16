package com.milenyumsoft.springsecurity.controller;


import com.milenyumsoft.springsecurity.dto.AuthLoginRequestDTO;
import com.milenyumsoft.springsecurity.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

        @Autowired
        private UserDetailsServiceImp userDetailsService;

        //Todas estas requests y responses vamos a tratarlas como dto
        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
            return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
        }

    }



