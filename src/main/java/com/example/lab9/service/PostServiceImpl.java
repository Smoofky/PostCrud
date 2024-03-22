package com.example.lab9.service;

import com.example.lab9.dto.PostDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.Like;
import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import com.example.lab9.repository.LikeRepository;
import com.example.lab9.repository.PostRepository;
import com.example.lab9.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LikeRepository likeRepository;

    @Override
    @Transactional
    public void deletePost(Long postId) {
        // Usunięcie posta na podstawie jego ID
        postRepository.deleteById(postId);
    }
    
    @Override
    public List<Post> getPostsByUser(User user) {
        // Pobranie listy postów dla danego użytkownika
        return postRepository.findByUser(user);
    }

    @Override
    public void savePost(Post post) {
        // Zapisanie nowego posta
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        // Pobranie posta po jego ID
        return postRepository.findById(postId).orElse(null);
    }
    
    @Override
    public void addCommentToPost(Long postId, Comment comment) {
        // Dodanie komentarza do posta
        Post post = getPostById(postId);
        if (post != null) {
            comment.setPost(post);
            post.getComments().add(comment);
            postRepository.save(post);
        }
    }
    
    @Override
    public List<PostDto> getAllPosts() {
        // Pobranie listy wszystkich postów
        return postRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private PostDto convertToDto(Post post) {
        // Konwersja obiektu Post na obiekt PostDto
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setAuthor(post.getAuthor());
        postDto.setLikes(post.getLikes());
        postDto.setAddedDate(post.getAddedDate());
        postDto.setTitle(post.getTitle());
        postDto.setImageUrl(post.getUser().getImageUrl());
        return postDto;
    }
    
    @Override
    public void editPost(Long postId, String newContent, String newTitle) {
        // Edycja treści i tytułu posta
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setContent(newContent);
            post.setTitle(newTitle);
            postRepository.save(post);
        }
    }

    @Override
    @Transactional
    public void likePost(Long postId, String userId) {
        // Dodanie polubienia do posta
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByUsername(userId);

        if (post != null && user != null) {
            // Aktualizacja liczby polubień posta
            post.setLikes(post.getLikes() + 1);
            postRepository.save(post);

            // Utworzenie i zapisanie obiektu Like
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
        }
    }

    @Override
    @Transactional
    public void dislikePost(Long postId, String userId) {
        // Usunięcie polubienia z posta
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByUsername(userId);

        if (post != null && user != null) {
            // Aktualizacja liczby polubień posta
            post.setLikes(Math.max(0, post.getLikes() - 1));
            postRepository.save(post);

            // Usunięcie polubienia z repozytorium
            likeRepository.deleteByUserAndPost(user, post);
        }
    }
    
    @Override
    public List<Long> getLikedPostIdsForUser(Long userId) {
        // Pobranie listy ID postów, które użytkownik polubił
        List<Like> userLikes = likeRepository.findByUserId(userId);
        List<Long> likedPostIds = userLikes.stream()
                .map(like -> like.getPost().getId())
                .collect(Collectors.toList());
        return likedPostIds;
    }
}
