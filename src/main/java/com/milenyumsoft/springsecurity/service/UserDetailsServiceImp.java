package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.UserSec;
import com.milenyumsoft.springsecurity.repository.IUserSecRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    @Autowired
    private IUserSecRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       //tenemos User sec y necestiamos devolver UserDetails
        //traemos el usuario de la bd

        UserSec userSec = userRepo.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(" El usuario " + username + " No fue encontrado"));


        //con GrantedAuthority spring security maneja permisos

        List<GrantedAuthority> authorityList = new ArrayList<>();

        //Programación funcional a full
        //Tomamos roles y los convertimos en simple grantedauthority para poder agregasrlos a  la authoritylist

        userSec.getRolesList()
                .forEach(role->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));


        //ahora tenemos que agregar los permisoss

        userSec.getRolesList().stream()
                .flatMap(role-> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));


        //retornamos el usuario en formato Spring Security con los daot de nuestro user

        return new User(userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotEcpired(),
                userSec.isAccountNotLocked(),
                authorityList);

    }
}
