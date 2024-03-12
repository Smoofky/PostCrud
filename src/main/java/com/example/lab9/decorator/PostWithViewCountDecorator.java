package com.example.lab9.decorator;

import com.example.lab9.dto.PostComponent;

// Tydzień 3, Decorator 4/4

// Można teraz tworzyć określone dekoratory, które rozszerzają
// PostDecorator o dodatkowe funkcjonalności.

public class PostWithViewCountDecorator extends PostDecorator {
    private int viewCount;

    public PostWithViewCountDecorator(PostComponent decoratedPost, int viewCount) {
        super(decoratedPost);
        this.viewCount = viewCount;
    }

    public int getViewCount() {
        return viewCount;
    }

}

// Tydzień 3, Decorator KONIEC 4/4
// Taka implementacja pozwala utrzymać kod DTO i modeli "czysto", oraz sprawić aby
// dzialaly zgodnie z zasadą pojedynczej odpowiedzialności
// jednocześnie zapewnia elastyczny sposób rozszerzania ich

