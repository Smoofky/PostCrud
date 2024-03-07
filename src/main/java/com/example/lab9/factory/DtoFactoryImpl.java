package com.example.lab9.factory;

import com.example.lab9.dto.PostDto;
import com.example.lab9.dto.UserDto;
import com.example.lab9.model.Post;
import com.example.lab9.dto.CommentDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.User;
import com.example.lab9.service.UserService;
import com.example.lab9.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoFactoryImpl implements DtoFactory {

    @Override
    public PostDto createPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setAuthor(post.getAuthor());
        postDto.setLikes(post.getLikes());
        postDto.setAddedDate(post.getAddedDate());
        postDto.setTitle(post.getTitle());
//        postDto.setImageUrl(post.getUser().getImageUrl());
        return postDto;
    }

    @Autowired
    private UserService userService;


    @Override
    public CommentDto createCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setLikes(comment.getLikes());
        commentDto.setAddedDate(comment.getAddedDate());
        commentDto.setPostId(comment.getPost().getId());
//        commentDto.setImageUrl(commentDto.getUser().);
        return commentDto;
    }
}
