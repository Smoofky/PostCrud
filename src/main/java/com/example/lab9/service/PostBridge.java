package com.example.lab9.service;

import com.example.lab9.dto.PostDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import java.util.List;

public interface PostBridge {
    List<PostDto> getAllPosts();

    List<Post> getPostsByUser(User user);

    void savePost(Post post);

    Post getPostById(Long postId);

    void addCommentToPost(Long postId, Comment comment);

    void deletePost(Long postId);

    void editPost(Long postId, String content, String title);

    void likePost(Long postId, String userId);

    void dislikePost(Long postId, String userId);

    List<Long> getLikedPostIdsForUser(Long userId);
}