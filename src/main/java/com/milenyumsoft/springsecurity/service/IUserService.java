package com.milenyumsoft.springsecurity.service;
import com.milenyumsoft.springsecurity.modelo.UserSec;
import java.util.List;
import java.util.Optional;

public interface IUserService {

     List<UserSec> findAll();
     Optional<UserSec> findById(Long id);
     UserSec save(UserSec userSec);
     void deleteById(Long id);
     UserSec update(UserSec userSec);
}
