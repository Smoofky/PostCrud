package com.example.lab9.service;

import com.example.lab9.dto.CommentDto;
import com.example.lab9.dto.PostDto;
import com.example.lab9.dto.UserDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeService {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public FacadeService(CommentService commentService, PostService postService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    
//Tydzień 4, Stosowanie strukturalnych wzorców projektowych cz. 2, Wzorzec Facade

    
    
    // Metody fasady do zarządzania różnymi funkcjonalnościami

    // Metody związane z komentarzami

    public List<CommentDto> getCommentsByPostId(Long postId) {
        // Pobieranie komentarzy dla danego ID posta za pomocą CommentService
        return commentService.getCommentsByPostId(postId);
    }

    public void saveComment(Comment comment) {
        // Zapisywanie komentarza za pomocą CommentService
        commentService.saveComment(comment);
    }

    public void deleteComment(Long commentId) {
        // Usuwanie komentarza za pomocą CommentService
        commentService.deleteComment(commentId);
    }

    public CommentDto getCommentById(Long commentId) {
        // Pobieranie komentarza po ID za pomocą CommentService
        return commentService.getCommentById(commentId);
    }

    public void editComment(Long commentId, String editedContent) {
        // Edycja komentarza za pomocą CommentService
        commentService.editComment(commentId, editedContent);
    }

    // Metody związane z postami

    public List<PostDto> getAllPosts() {
        // Pobieranie wszystkich postów za pomocą PostService
        return postService.getAllPosts();
    }

    public List<Post> getPostsByUser(User user) {
        // Pobieranie postów użytkownika za pomocą PostService
        return postService.getPostsByUser(user);
    }

    public void savePost(Post post) {
        // Zapisywanie posta za pomocą PostService
        postService.savePost(post);
    }

    public Post getPostById(Long postId) {
        // Pobieranie posta po ID za pomocą PostService
        return postService.getPostById(postId);
    }

    public void addCommentToPost(Long postId, Comment comment) {
        // Dodawanie komentarza do posta za pomocą PostService
        postService.addCommentToPost(postId, comment);
    }

    public void deletePost(Long postId) {
        // Usuwanie posta za pomocą PostService
        postService.deletePost(postId);
    }

    public void editPost(Long postId, String content, String title) {
        // Edycja posta za pomocą PostService
        postService.editPost(postId, content, title);
    }

    public void likePost(Long postId, String userId) {
        // Dodawanie polubienia do posta za pomocą PostService
        postService.likePost(postId, userId);
    }

    public void dislikePost(Long postId, String userId) {
        // Dodawanie niepolubienia do posta za pomocą PostService
        postService.dislikePost(postId, userId);
    }

    public List<Long> getLikedPostIdsForUser(Long userId) {
        // Pobieranie ID postów polubionych przez użytkownika za pomocą PostService
        return postService.getLikedPostIdsForUser(userId);
    }

    // Metody związane z użytkownikami

    public User findByUsername(String username) {
        // Pobieranie użytkownika po nazwie użytkownika za pomocą UserService
        return userService.findByUsername(username);
    }

    public void saveUser(User user) {
        // Zapisywanie użytkownika za pomocą UserService
        userService.saveUser(user);
    }

    public boolean isUsernameTaken(String username) {
        // Sprawdzanie, czy nazwa użytkownika jest zajęta za pomocą UserService
        return userService.isUsernameTaken(username);
    }

    public UserDto getUserById(Long id) {
        // Pobieranie użytkownika po ID za pomocą UserService
        return userService.getUserById(id);
    }

    public UserDto getUserDtoByUsername(String username) {
        // Pobieranie DTO użytkownika po nazwie użytkownika za pomocą UserService
        return userService.getUserDtoByUsername(username);
    }

    public long countPostsByUsername(String username) {
        // Liczenie postów użytkownika po nazwie użytkownika za pomocą UserService
        return userService.countPostsByUsername(username);
    }

    public long countCommentsByUsername(String username) {
        // Liczenie komentarzy użytkownika po nazwie użytkownika za pomocą UserService
        return userService.countCommentsByUsername(username);
    }

    public long countLikesByUsername(String username) {
        // Liczenie polubień użytkownika po nazwie użytkownika za pomocą UserService
        return userService.countLikesByUsername(username);
    }
}

//Koniec, Tydzień 4, Wzorzec Facade.