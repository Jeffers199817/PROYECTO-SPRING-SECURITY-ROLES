package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.UserSec;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserSec> findAll();
    public Optional<UserSec> findById(Long id);
    public UserSec save(UserSec userSec);
    public void deleteById(Long id);
    public UserSec update(UserSec userSec);
}
