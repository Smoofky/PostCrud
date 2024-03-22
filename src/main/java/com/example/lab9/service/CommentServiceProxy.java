package com.example.lab9.service;

import com.example.lab9.dto.CommentDto;
import com.example.lab9.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
public class CommentServiceProxy implements CommentService {

    private final CommentService commentService;

    @Autowired
    public CommentServiceProxy(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        System.out.println("Proxy: Fetched " + comments.size() + " comments.");
        return comments;
    }

    @Override
    public void saveComment(Comment comment) {
        commentService.saveComment(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        System.out.println("Proxy: Deleting comment ID: " + commentId);
        commentService.deleteComment(commentId);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        System.out.println("Proxy: Fetching comment ID: " + commentId);
        Comment comment = commentService.getCommentById(commentId);
        return comment;
    }

    @Override
    public void editComment(Long commentId, String editedContent) {
        System.out.println("Proxy: Editing comment ID: " + commentId);
        commentService.editComment(commentId, editedContent);
    }
}
