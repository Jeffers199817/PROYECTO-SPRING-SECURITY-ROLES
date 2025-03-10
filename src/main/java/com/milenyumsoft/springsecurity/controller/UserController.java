package com.milenyumsoft.springsecurity.controller;

import com.milenyumsoft.springsecurity.modelo.Role;
import com.milenyumsoft.springsecurity.modelo.UserSec;
import com.milenyumsoft.springsecurity.service.IRoleService;
import com.milenyumsoft.springsecurity.service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List> getAllUsers(){

        List users = userService.findAll();

        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        Optional user = userService.findById(id);
        return (ResponseEntity) user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserSec userSec) {

        Set<Role> roleList = new HashSet<>();
        Role readRole;


        //Recuperar la Permission/s por su ID
        for (Role role : userSec.getRolesList()) {

            readRole = (Role) roleService.findById(role.getId()).orElse(null);
            if (readRole != null) {
                //si encuentro , guardo en la lista
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {

            userSec.setRolesList(roleList);

            UserSec newUser = userService.save(userSec);
            return ResponseEntity.ok(newUser);

        }

        return null;
    }

}
