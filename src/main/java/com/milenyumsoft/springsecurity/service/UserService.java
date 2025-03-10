package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.UserSec;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{


    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserSec save(UserSec userSec) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public UserSec update(UserSec userSec) {
        return null;
    }
}
