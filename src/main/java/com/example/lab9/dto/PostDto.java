/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab9.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author rytel
 */
public class PostDto implements PostComponent {

    private Long id; // If you need to transfer the post id
    private String content;
    private String author;
    private int likes;
    private Date addedDate;
    private List<CommentDto> comments;
    private String title;
    private String imageUrl;  // Add this field
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    // Tydzień 3, Decorator 2/4
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getContent() {
        return this.content;
    }
    // Tydzień 3, Decorator KONIEC 2/4
}
