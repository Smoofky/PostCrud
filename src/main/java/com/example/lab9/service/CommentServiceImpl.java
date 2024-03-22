package com.example.lab9.service;

import com.example.lab9.dto.CommentDto;
import com.example.lab9.model.Comment;
import com.example.lab9.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // Implementacja pobierania komentarzy po ID posta
        return null;
        // Implementacja pobierania komentarzy po ID posta
    }

    @Override
    public void saveComment(Comment commentDto) {
        // Implementacja zapisywania komentarza
    }

    @Override
    public void deleteComment(Long commentId) {
        // Implementacja usuwania komentarza
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        // Implementacja pobierania komentarza po ID
        return null;
        // Implementacja pobierania komentarza po ID
    }

    @Override
    public void editComment(Long commentId, String editedContent) {
        // Implementacja edycji komentarza
    }
}
