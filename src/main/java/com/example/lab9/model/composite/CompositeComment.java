package com.example.lab9.model.composite;

import java.util.ArrayList;
import java.util.List;

//Tydzień 3, Stosowanie strukturalnych wzorców projektowych cz. 1, Wzorzec Composite

//Ta klasa implementuje interfejs CommentComponent. 
//CompositeComment reprezentuje złożony komentarz, który może zawierać inne komentarze (zarówno pojedyncze, jak i złożone).


public class CompositeComment implements CommentComponent {

    private List<CommentComponent> replies = new ArrayList<>();

    @Override
    public void addReply(CommentComponent reply) {
        replies.add(reply);
    }

    @Override
    public void removeReply(CommentComponent reply) {
        replies.remove(reply);
    }

    @Override
    public List<CommentComponent> getReplies() {
        return replies;
    }

    @Override
    public boolean isComposite() {
        return true;
    }
}

//Koniec, Tydzień 3, Wzorzec Composite.
