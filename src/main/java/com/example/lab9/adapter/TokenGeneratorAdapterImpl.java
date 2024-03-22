/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab9.adapter;

import com.example.lab9.config.JwtTokenUtil;
import com.example.lab9.config.TokenGeneratorAdapter;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

/** START
 * Tydzień 3 
 * Adapter
 * Klasą bazową jest Klasa JwtTokenUtil, w której znajdują się operacje dotyczące tokena JWT oraz informacji z niego wynikających/z nim związanych (np. funkcja getUserFromJwt).
 * Stworzono interfejs TokenGeneratorAdapter, definujący metody generowania tokena, który implementuje klasa adaptera.
 * Czyli dwie klasy mające różne interfejsy komunikują się ze sobą poprzez dziedziczenie.
 */
@Component
public class TokenGeneratorAdapterImpl extends JwtTokenUtil implements TokenGeneratorAdapter {
    
    @Override
    public String generateTokenSHA256(String username) {
        return super.generateToken(username, SignatureAlgorithm.HS256);
    }

    @Override
    public String generateTokenSHA512(String username) {
        return super.generateToken(username, SignatureAlgorithm.HS512);
    }

    @Override
    public String generateTokenSHA384(String username) {
        return super.generateToken(username, SignatureAlgorithm.HS384);
    }
    
    public String generateToken(String username, String algorithm){
        
        return switch (algorithm) {
            case "SHA256" -> generateTokenSHA256(username);
            case "SHA384" -> generateTokenSHA384(username);
            case "SHA512" -> generateTokenSHA512(username);
            default -> generateTokenSHA256(username);
        };  
    }
}
/** KONIEC
 */