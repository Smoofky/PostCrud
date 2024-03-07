/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab9.Builder;

import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import java.util.Date;
 /* --START
        TEMAT II
        Stosowanie konstrukcyjnych wzorców projektowych
        Użycie Buildera na przykładzie postów.
        Ważna metoda build, która ustala parametry dla
obiektu POST niejawnie, za pomocą buildera.
        */

public class PostBuilder {
    private String content;
    private String title;
    private String author;
    private Date addedDate;
    private int likes;
    private User user;

    public PostBuilder content(String content) {
        this.content = content;
        return this;
    }

    public PostBuilder title(String title) {
        this.title = title;
        return this;
    }

    public PostBuilder author(String author) {
        this.author = author;
        return this;
    }

    public PostBuilder addedDate(Date addedDate) {
        this.addedDate = addedDate;
        return this;
    }

    public PostBuilder likes(int likes) {
        this.likes = likes;
        return this;
    }

    public PostBuilder user(User user) {
        this.user = user;
        return this;
    }

    public Post build() {
        Post post = new Post();
        post.setContent(this.content);
        post.setTitle(this.title);
        post.setAuthor(this.author);
        post.setAddedDate(this.addedDate);
        post.setLikes(this.likes);
        post.setUser(this.user);
        return post;
    }
}
 
        /* --KONIEC
        TEMAT II
        Stosowanie konstrukcyjnych wzorców projektowych
        */