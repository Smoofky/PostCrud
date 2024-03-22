package com.example.lab9.service;

import com.example.lab9.dto.PostDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import java.util.List;

public class PostBridgeImpl implements PostBridge {

    private final PostService postService;

    public PostBridgeImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return postService.getPostsByUser(user);
    }

    @Override
    public void savePost(Post post) {
        postService.savePost(post);
    }

    @Override
    public Post getPostById(Long postId) {
        return postService.getPostById(postId);
    }

    @Override
    public void addCommentToPost(Long postId, Comment comment) {
        postService.addCommentToPost(postId, comment);
    }

    @Override
    public void deletePost(Long postId) {
        postService.deletePost(postId);
    }

    @Override
    public void editPost(Long postId, String content, String title) {
        postService.editPost(postId, content, title);
    }

    @Override
    public void likePost(Long postId, String userId) {
        postService.likePost(postId, userId);
    }

    @Override
    public void dislikePost(Long postId, String userId) {
        postService.dislikePost(postId, userId);
    }

    @Override
    public List<Long> getLikedPostIdsForUser(Long userId) {
        return postService.getLikedPostIdsForUser(userId);
    }
}