package com.example.lab9.factory;

import com.example.lab9.dto.PostDto;
import com.example.lab9.model.Post;
import com.example.lab9.dto.CommentDto;
import com.example.lab9.model.Comment;



// Tydzień 1, Wzorzec Fabryka 1/4
public interface DtoFactory {
    PostDto createPostDto(Post post);
    CommentDto createCommentDto(Comment comment);
}
// Tydzień 1, Wzorzec Fabryka 1/4 KONIEC
