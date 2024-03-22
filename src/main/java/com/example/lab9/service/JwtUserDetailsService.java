package com.example.lab9.service;

import com.example.lab9.model.User;
import com.example.lab9.dto.UserDto;
import com.example.lab9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;

//Tydzień 2, Stosowanie konstrukcyjnych wzorców projektowych, Wzorzec Singleton
    //W tym kodzie zastosowano technikę Singleton poprzez leniwą inicjalizację (Lazy Initialization) 
    //z synchronizacją za pomocą metody getInstance().
    
    // Singleton instance
    private static JwtUserDetailsService instance;

    // Prywatny konstruktor, aby zapobiec instancjonowaniu spoza tej klasy
    private JwtUserDetailsService() {
    }

    // Metoda do uzyskania instancji Singletona
    public static synchronized JwtUserDetailsService getInstance() {
        if (instance == null) {
            instance = new JwtUserDetailsService();
        }
        return instance;
    }

//Koniec, Tydzień 2, Wzorzec Singleton.

    
    
    
    
    // Lista URLi obrazków dla nowych użytkowników
    public final List<String> imageUrls = Arrays.asList(
            "https://mdbcdn.b-cdn.net/img/new/avatars/8.webp",
            "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(11).webp",
            "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(10).webp",
            // Inne adresy URL...
            "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(30).webp"
    );

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Użytkownik o nazwie: "
                    + username + " nie został znaleziony");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    // Metoda do zapisywania nowego użytkownika
    public User save(UserDto user) {
        Random rand = new Random();
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setImageUrl(imageUrls.get(rand.nextInt(imageUrls.size())));
        return userDao.save(newUser);
    }

    // Metoda sprawdzająca, czy nazwa użytkownika jest już zajęta
    public boolean isUsernameTaken(String username) {
        return userDao.findByUsername(username) != null;
    }
}
