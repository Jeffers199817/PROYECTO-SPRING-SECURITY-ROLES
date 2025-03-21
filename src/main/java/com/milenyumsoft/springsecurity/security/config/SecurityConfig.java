package com.milenyumsoft.springsecurity.security.config;

import com.milenyumsoft.springsecurity.utils.JwtUtils;
import com.milenyumsoft.springsecurity.security.config.filter.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    private JwtUtils jwtUtils;
    //1.-Permite agregar los permisos.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{

        /*return httpSecurity
                .csrf(csrf->csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http ->{
                    //Endpoints

                    http.requestMatchers(HttpMethod.GET, "/holanoseg").permitAll();
                    http.requestMatchers(HttpMethod.GET,"/holaseg").hasAuthority("READ");
                    http.anyRequest().denyAll();
                })*/
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
        .build();
    }


    //2.Permite la gestion de la autenticación administración de authenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }


    //3.-proveedor de autenticación

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());

        //sacamos el anteriro , el lógico y agreamos el nuvo
        provider.setUserDetailsService(userDetailsService);


       /* DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());

        */
        return provider;
    }


    //4.-Password
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    //5Configurar nuestro userdetailsservice

    /*@Bean
    public UserDetailsService userDetailsService(){

        List userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("milenyum")
                .password("12345")
                .roles("ADMIN")
                .authorities("CREATE","READ","UPDATE","DELETE")
                .build());




        userDetailsList.add(User.withUsername("seguidor")
                .password("12345")
                .roles("USER")
                .authorities("READ")
                .build());



        userDetailsList.add(User.withUsername("actualizador")
                .password("12345")
                .roles("USER")
                .authorities("UPDATE")
                .build());


        return new InMemoryUserDetailsManager(userDetailsList);
    } */

}
