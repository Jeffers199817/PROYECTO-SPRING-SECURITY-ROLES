package com.milenyumsoft.springsecurity.utils;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JwtUtils {

    //CON estas configuraciones aseguramos la autenticicdad del totken a crear
    @Value("${security.jwt.private.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;


    //Para encriptar, vamos a necesitar esta clave secreta y este algoritmo

    public String createToken(Authentication authentication){

        //1.Creamos un objeto de tipo algorithm
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        //2.Esto está dentro del security context holder traemos el username
        String username = authentication.getPrincipal().toString();

        //3.-también obtenemos los permisso/ autorizacione s
        // la idea es traer los permisos separdos por coma
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //4- Generamos el token apartir de lo demas




        return jwtToken;
    }

}
