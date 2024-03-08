package com.example.lab9.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "content", length = 1000)
    private String content;
    @Column(name = "title", length = 50)
    private String title;
    private String author;
    private Date addedDate;
    private int likes;
    private Date removalDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(Date removalDate) {
        this.removalDate = removalDate;
    }

    public List<Like> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Like> likesList) {
        this.likesList = likesList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Private constructor to be used only by the builder
    private Post(PostBuilder builder) {
        this.content = builder.content;
        this.title = builder.title;
        this.author = builder.author;
        this.addedDate = builder.addedDate;
        this.likes = builder.likes;
        this.user = builder.user;
        this.removalDate = builder.removalDate;
    }

    public static class PostBuilder {
        private String content;
        private String title;
        private String author;
        private Date addedDate;
        private int likes;
        private User user;
        private Date removalDate;

        public PostBuilder(String content, String title, String author, Date addedDate, int likes, User user) {
            this.content = content;
            this.title = title;
            this.author = author;
            this.addedDate = addedDate;
            this.likes = likes;
            this.user = user;
        }

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

        // Method to set removal date 15 days ahead of added date
        public PostBuilder removalDate(Date removalDate) {
            this.removalDate = removalDate;
            return this;
        }

        // Method to set removal date 15 days ahead of added date by default
        public PostBuilder removalDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.addedDate);
            calendar.add(Calendar.DAY_OF_MONTH, 15);
            this.removalDate = calendar.getTime();
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
