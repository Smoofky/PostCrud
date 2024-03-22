package com.example.lab9.adapter;

import com.example.lab9.dto.CommentDto;
import com.example.lab9.dto.UserDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.User;
// Tydzien 3, Adapter
public class CommentAdapter {

    public static CommentDto commentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setPostId(comment.getPost().getId());
        commentDto.setLikes(comment.getLikes());
        commentDto.setAddedDate(comment.getAddedDate());

        User user = comment.getUser();
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setImageUrl(user.getImageUrl()); 
        commentDto.setUser(userDto);
        return commentDto;
    }
}