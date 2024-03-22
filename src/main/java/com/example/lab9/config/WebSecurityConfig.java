package com.example.lab9.config;

import com.example.lab9.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    private JwtUserDetailsService jwtUserDetailsService; // Deklaracja zmiennej dla instancji JwtUserDetailsService
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService()).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/authenticate", "/register", "/login").permitAll()
                .anyRequest().authenticated())
                .exceptionHandling(auth -> auth.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    //Tydzień 2, Stosowanie konstrukcyjnych wzorców projektowych, Wzorzec Singleton
    //W tym kodzie zastosowano technikę Singleton poprzez leniwą inicjalizację (Lazy Initialization) 
    //z synchronizacją za pomocą metody getInstance().
    
    
    // Metoda inicjalizująca pole jwtUserDetailsService
    private JwtUserDetailsService jwtUserDetailsService() {
        if (jwtUserDetailsService == null) { // czy instancja już istnieje
            jwtUserDetailsService = JwtUserDetailsService.getInstance(); // Uzyskanie instancji JwtUserDetailsService przez getInstance()
        }
        return jwtUserDetailsService; // Zwrócenie instancji JwtUserDetailsService
    }
}

//Koniec, Tydzień 2, Wzorzec Singleton.