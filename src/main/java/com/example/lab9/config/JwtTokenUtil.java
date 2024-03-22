package com.example.lab9.config;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

//Tydzień 2, Stosowanie konstrukcyjnych wzorców projektowych, Wzorzec Singleton 
//W tym kodzie również zastosowano technikę Singleton poprzez leniwą inicjalizację (Lazy Initialization) 
    //z synchronizacją za pomocą metody getInstance().
    
    // Singleton instance
    private static JwtTokenUtil instance;

    @Value("${jwt.secret}")
    private String secret;

    private JwtTokenUtil() {
        // Prywatny konstruktor, aby zapobiec instancjonowaniu spoza tej klasy
    }

    // Metoda do uzyskania instancji Singletona
    public static synchronized JwtTokenUtil getInstance() {
        if (instance == null) {
            instance = new JwtTokenUtil();
        }
        return instance;
    }


//Koniec, Tydzień 2, Wzorzec Singleton.
  
    // Metoda do pobierania nazwy użytkownika z tokena
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Metoda do pobierania daty wydania tokena
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    // Metoda do pobierania daty wygaśnięcia tokena
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Metoda do pobierania określonego claima z tokena
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Metoda do parsowania i pobierania wszystkich claimów z tokena
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret).parseClaimsJws(token)
                .getBody();
    }

    // Metoda do sprawdzania, czy token wygasł
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Metoda do ignorowania wygaśnięcia tokenu (obecnie nie używana)
    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    // Metoda do generowania tokenu na podstawie UserDetails
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Metoda do budowania i podpisywania tokenu JWT
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // Metoda do sprawdzania, czy token może być odświeżony
    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    // Metoda do walidacji tokenu
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }
}
