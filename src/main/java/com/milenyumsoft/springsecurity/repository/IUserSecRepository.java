package com.milenyumsoft.springsecurity.repository;

import com.milenyumsoft.springsecurity.modelo.UserSec;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserSecRepository extends JpaRepository<UserSec, Long> {



    //CREA LA SENTENCIA EN BASE AL NOMBRE EN INGLES EL METODO
    //TAMBIEN SE PUEDE HACER MEDIANTE QUIRY PERO EN ESTE CADO NO ES NECSARIO

    Optional<UserSec> findUserEntityByUsername(String username);
}
