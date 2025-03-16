package com.milenyumsoft.springsecurity.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
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

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)// acá va el usuario que genera el token
                .withSubject(username) //a quien se le genera el token
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwtToken;
    }

    //método para decodificar
    public DecodedJWT validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey); //algoritmo + clave privada
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build(); //usa patrón builder

            //si está todo ok, no genera excepción y hace el return
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        }
        catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid token. Not authorized");
        }
    }


    public String extractUsername (DecodedJWT decodedJWT) {
        //el subject es el usuario según establecimos al crear el token
        return decodedJWT.getSubject().toString();
    }

    //devuelvo un claim en particular
    public Claim getSpecificClaim (DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    //devuelvo todos los claims
    public Map<String, Claim> returnAllClaims (DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }



}
