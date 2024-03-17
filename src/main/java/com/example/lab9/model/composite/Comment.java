package com.example.lab9.model.composite;

import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

//Tydzień 3, Stosowanie strukturalnych wzorców projektowych cz. 1, Wzorzec Composite
//Klasa Comment została zmodyfikowana tak, aby implementować interfejs CommentComponent, co pozwala jej być traktowaną jako część struktury kompozytu.

@Entity
@Table(name = "comment")
public class Comment implements CommentComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int likes;
    @Lob
    @Column(name = "content", length = 1000)
    private String content;
    private String author;
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
 //Metody addReply i removeReply umożliwiają dodawanie i usuwanie odpowiedzi na dany komentarz, co jest kluczową częścią wzorca Composite.

    @Override
    public void addReply(CommentComponent reply) {
       
    }

    @Override
    public void removeReply(CommentComponent reply) {
        
    }

    
//Metoda getReplies zwraca listę odpowiedzi dla danego komentarza. W tym przypadku zwracana lista jest pusta, ponieważ ta funkcjonalność nie została jeszcze zaimplementowana, 
//ale ta metoda jest często używana w przypadku złożonych kompozytów do pobierania podkomentarzy.
    
    
    public List<CommentComponent> getReplies() {
       
        return null;
    }

//Metoda isComposite zwraca wartość false, co oznacza, że ten konkretny komentarz nie jest kompozytem, ale pojedynczym elementem w strukturze.
    
    public boolean isComposite() {
        return false;
    }

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


//Koniec, Tydzień 3, Wzorzec Composite.
