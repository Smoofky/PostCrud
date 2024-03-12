package com.example.lab9.decorator;

import com.example.lab9.dto.PostComponent;

// Tydzień 3, Decorator 3/4

// Ta klasa implementuje interfejs PostComponent i przechowywuje odwołanie do
// obiektu PostComponent. Wszystkie metody interfejsu zostaną przypisane do obiektu
// PostComponent, a podklasy tego dekoratora dodadzą dodatkowe funkcjonalności.
public abstract class PostDecorator implements PostComponent {
    protected PostComponent decoratedPost;

    public PostDecorator(PostComponent decoratedPost) {
        this.decoratedPost = decoratedPost;
    }

    @Override
    public Long getId() {
        return decoratedPost.getId();
    }

    @Override
    public String getContent() {
        return decoratedPost.getContent();
    }

}
// Tydzień 3, Decorator KONIEC 3/4