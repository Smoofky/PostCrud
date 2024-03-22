package com.example.lab9.service;

import com.example.lab9.dto.UserDto;
import com.example.lab9.model.User;

//Tydzień 4, Stosowanie strukturalnych wzorców projektowych cz. 2, Wzorzec Facade

public interface UserService {

    // Metoda do pobierania użytkownika po nazwie użytkownika
    User findByUsername(String username);

    // Metoda do zapisywania użytkownika
    void saveUser(User user);

    // Metoda do sprawdzania, czy nazwa użytkownika jest już zajęta
    boolean isUsernameTaken(String username);

    // Metoda do pobierania obiektu DTO użytkownika po jego ID
    UserDto getUserById(Long id);

    // Metoda do pobierania obiektu DTO użytkownika po jego nazwie użytkownika
    UserDto getUserDtoByUsername(String username);

    // Metoda do zliczania postów użytkownika na podstawie jego nazwy użytkownika
    long countPostsByUsername(String username);

    // Metoda do zliczania komentarzy użytkownika na podstawie jego nazwy użytkownika
    long countCommentsByUsername(String username);

    // Metoda do zliczania polubień użytkownika na podstawie jego nazwy użytkownika
    long countLikesByUsername(String username);
}

//Koniec, Tydzień 4, Wzorzec Facade.