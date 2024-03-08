package com.example.lab9.controller;

import com.example.lab9.dto.UserDto;
import com.example.lab9.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin
public class RegisterController {

    
  //Tydzień 2, Stosowanie konstrukcyjnych wzorców projektowych, Wzorzec Singleton
    //W tym kodzie zastosowano technikę Singleton poprzez leniwą inicjalizację (Lazy Initialization) 
    //z synchronizacją za pomocą metody getInstance().
    //Użycie!
    
    
    // Użycie Singletona JwtUserDetailsService
    private final JwtUserDetailsService userDetailsService = JwtUserDetailsService.getInstance();

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto user, Model model) {
        try {
            // Sprawdzenie, czy nazwa użytkownika jest zajęta
            if (userDetailsService.isUsernameTaken(user.getUsername())) {
                model.addAttribute("error", "Username is already taken. Please choose a different one.");
                return "redirect:/register";
            }
           //Zapisanie nowego użytkownika
            userDetailsService.save(user);
            return "redirect:/login?success=registered";
        } catch (Exception e) {
            model.addAttribute("error", "Something went wrong. Please try again.");
            return "register";
        }
    }
}
//Koniec, Tydzień 2, Wzorzec Singleton.
