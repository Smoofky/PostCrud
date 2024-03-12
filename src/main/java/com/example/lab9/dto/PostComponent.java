package com.example.lab9.dto;

// Tydzień 3, Decorator 1/4

// Najpierw zdefiniowalem interfejs dla swojego DTO, aby mieć pewność,
// że zarówno oryginalne obiekty,
// jak i ich dekoratory będą miały ten sam typ podstawowy.

public interface PostComponent {
    // Methods that are in the original PostDto
    Long getId();
    String getContent();
    // Add other methods as required
    // New method signatures for functionalities can also be added here
}

// Tydzień 3, Decorator KONIEC 1/4