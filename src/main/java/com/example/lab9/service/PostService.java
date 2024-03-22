package com.example.lab9.service;

import com.example.lab9.dto.PostDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.Post;
import com.example.lab9.model.User;

import java.util.List;

//Tydzień 4, Stosowanie strukturalnych wzorców projektowych cz. 2, Wzorzec Facade

public interface PostService {

    // Pobranie listy wszystkich postów
    List<PostDto> getAllPosts();

    // Pobranie listy postów dla danego użytkownika
    List<Post> getPostsByUser(User user);

    // Zapisanie nowego posta
    void savePost(Post post);

    // Pobranie posta po jego ID
    Post getPostById(Long postId);

    // Dodanie komentarza do posta
    void addCommentToPost(Long postId, Comment comment);

    // Usunięcie posta
    void deletePost(Long postId);

    // Edycja treści i tytułu posta
    void editPost(Long postId, String content, String title);

    // Dodanie polubienia do posta
    void likePost(Long postId, String userId);

    // Dodanie niepolubienia do posta
    void dislikePost(Long postId, String userId);

    // Pobranie listy ID postów, które zostały polubione przez użytkownika
    List<Long> getLikedPostIdsForUser(Long userId);
}

//Koniec, Tydzień 4, Wzorzec Facade.