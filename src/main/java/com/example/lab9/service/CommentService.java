package com.example.lab9.service;

import com.example.lab9.dto.CommentDto;
import com.example.lab9.model.Comment;

import java.util.List;


//Tydzień 4, Stosowanie strukturalnych wzorców projektowych cz. 2, Wzorzec Facade

// Interfejs CommentService stanowi fasadę dla usług związanych z komentarzami.
public interface CommentService {

    // Metoda służąca do pobierania komentarzy dla danego identyfikatora posta.
    List<CommentDto> getCommentsByPostId(Long postId);

    // Metoda do zapisywania nowego komentarza.
    void saveComment(Comment commentDto);

    // Metoda do usuwania istniejącego komentarza na podstawie jego identyfikatora.
    void deleteComment(Long commentId);

    // Metoda do pobierania komentarza na podstawie jego identyfikatora.
    CommentDto getCommentById(Long commentId);

    // Metoda do edytowania istniejącego komentarza na podstawie jego identyfikatora i nowej zawartości.
    void editComment(Long commentId, String editedContent);
}

//Koniec, Tydzień 4, Wzorzec Facade.