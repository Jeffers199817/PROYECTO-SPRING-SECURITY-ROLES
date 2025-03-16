package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.dto.AuthLoginRequestDTO;
import com.milenyumsoft.springsecurity.dto.AuthResponseDTO;
import com.milenyumsoft.springsecurity.modelo.UserSec;
import com.milenyumsoft.springsecurity.repository.IUserSecRepository;
import com.milenyumsoft.springsecurity.utils.JwtUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private IUserSecRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       //tenemos User sec y necestiamos devolver UserDetails
        //traemos el usuario de la bd

        UserSec userSec = userRepo.findUserEntityByUsername(username)

                .orElseThrow(() -> new UsernameNotFoundException(" El usuario " + username + " No fue encontrado"));


        //con GrantedAuthority spring security maneja permisos

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //Programación funcional a full
        //Traer roles y convertirlos en siplegrantedauthority


        userSec.getRolesList()
                .forEach(role->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));


        //traer permisos y convertirlos en simpleGrntesautority

        userSec.getRolesList().stream()
                .flatMap(role-> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));


        //retornamos el usuario en formato Spring Security con los daot de nuestro user

        return new User(userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired(),
                userSec.isAccountNotLocked(),
                authorityList);

    }


    public AuthResponseDTO loginUser (AuthLoginRequestDTO authLoginRequest){

        //recuperamos nombre de usuario y contraseña
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate (username, password);
        //si todo sale bien
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "login ok", accessToken, true);
        return authResponseDTO;

    }
    public Authentication authenticate (String username, String password) {
        //con esto debo buscar el usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        // si no es igual
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }


}
