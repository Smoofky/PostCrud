package com.example.lab9.service;

import com.example.lab9.dto.UserDto;
import com.example.lab9.model.User;
import com.example.lab9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User findByUsername(String username) {
        // Metoda pobierająca użytkownika z repozytorium na podstawie nazwy użytkownika
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User userDto) {
        // Metoda do zapisywania nowego użytkownika
        if (isUsernameTaken(userDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        
        userRepository.save(user);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        // Metoda sprawdzająca, czy nazwa użytkownika jest już zajęta
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        // Metoda pobierająca użytkownika na podstawie jego ID
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return mapUserToUserDto(user);
        } else {
            return null;
        }
    }

    @Override
    public UserDto getUserDtoByUsername(String username) {
        // Metoda pobierająca użytkownika na podstawie jego nazwy użytkownika
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return mapUserToUserDto(user);
        } else {
            return null;
        }
    }

    private UserDto mapUserToUserDto(User user) {
        // Metoda mapująca obiekt użytkownika na obiekt DTO użytkownika
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setImageUrl(user.getImageUrl());
        userDto.setUserId(user.getId());
        return userDto;
    }

    @Override
    public long countPostsByUsername(String username) {
        // Metoda zliczająca posty użytkownika na podstawie jego nazwy użytkownika
        return userRepository.countPostsByUsername(username);
    }

    @Override
    public long countCommentsByUsername(String username) {
        // Metoda zliczająca komentarze użytkownika na podstawie jego nazwy użytkownika
        return userRepository.countCommentsByUsername(username);
    }

    @Override
    public long countLikesByUsername(String username) {
        // Metoda zliczająca polubienia użytkownika na podstawie jego nazwy użytkownika
        return userRepository.countLikesByUsername(username);
    }   
}
