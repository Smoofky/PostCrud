/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab9.service;
import com.example.lab9.adapter.CommentAdapter; // Adapter modifikacja
import com.example.lab9.dto.CommentDto;
import com.example.lab9.dto.UserDto;
import com.example.lab9.model.Comment;
import com.example.lab9.model.Post;
import com.example.lab9.model.User;
import com.example.lab9.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(CommentAdapter::commentToDto)  // Adapter modifikacja
                .collect(Collectors.toList());
    }
    
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
    @Override
    public void saveComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setAuthor(commentDto.getAuthor());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null); // Adapter modifikacja
        return comment != null ? CommentAdapter.commentToDto(comment) : null; // Adapter modifikacja
    }

    @Override
    public void editComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            comment.setContent(newContent);
            commentRepository.save(comment);
        }
    }
}
