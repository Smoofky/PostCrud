package com.example.lab9.model.composite;

import java.util.List;



//Tydzień 3, Stosowanie strukturalnych wzorców projektowych cz. 1, Wzorzec Composite

//Ten interfejs definiuje podstawowe operacje, które muszą być wspólne dla wszystkich elementów w strukturze kompozytu. Jest to kluczowy element wzorca Composite.
 

public interface CommentComponent {
    void addReply(CommentComponent reply);
    void removeReply(CommentComponent reply);
    List<CommentComponent> getReplies();
    boolean isComposite();
}

    
//Koniec, Tydzień 3, Wzorzec Composite.
