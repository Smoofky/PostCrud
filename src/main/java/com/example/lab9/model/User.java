/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
     
    @Column
    private String imageUrl; // Add this field for the user's image URL
    
     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();
     
     /* ----- Start
     TYDZIEN  II 
     Stosowanie konstrukcyjnych wzorców projektowych
     PROTOTYP
     
     Klasa implementuje Cloneable - powoduje to, że zostaje
     utworzony klon bazowy obiektu User, który można modyfikować,
     np. przy dodawaniu użytkownika do bazy (rejestracja).
     */
     @Override
    public User clone() throws CloneNotSupportedException {
        User clonedUser = (User) super.clone();
        clonedUser.likes = new ArrayList<>(this.likes);
        return clonedUser;
    }
     /* ---- Koniec
     TYDZIEN  II 
     Stosowanie konstrukcyjnych wzorców projektowych
     PROTOTYP
     */
     
     
    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
