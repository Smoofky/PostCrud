package com.example.lab9.model.composite;

import java.util.List;


//Tydzień 3, Stosowanie strukturalnych wzorców projektowych cz. 1, Wzorzec Composite

//Ta klasa implementuje interfejs CommentComponent. 
// LeafComment reprezentuje pojedynczy komentarz, który nie ma podkomentarzy.


public class LeafComment implements CommentComponent {

    @Override
    public void addReply(CommentComponent reply) {
        
    }

    @Override
    public void removeReply(CommentComponent reply) {
        
    }

    @Override
    public List<CommentComponent> getReplies() {
        
        return null;
    }

    @Override
    public boolean isComposite() {
        return false;
    }
}

//Koniec, Tydzień 3, Wzorzec Composite.
