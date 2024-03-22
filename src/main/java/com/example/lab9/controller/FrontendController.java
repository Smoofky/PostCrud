package com.example.lab9.controller;
import com.example.lab9.dto.CommentDto;
import com.example.lab9.dto.PostDto;
import com.example.lab9.dto.UserDto;
import com.example.lab9.service.CommentService;
import com.example.lab9.service.PostService;
import com.example.lab9.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@CrossOrigin
public class FrontendController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloPage(Model model, HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        model.addAttribute("token", token);
        String loggedInUser = principal.getName();
        model.addAttribute("loggedInUser", loggedInUser);
        UserDto loggedInUserDto = userService.getUserDtoByUsername(loggedInUser);
        model.addAttribute("user", loggedInUserDto);
        long postCount = userService.countPostsByUsername(loggedInUser);
        long commentCount = userService.countCommentsByUsername(loggedInUser);
        long likesCount = userService.countLikesByUsername(loggedInUser);
        model.addAttribute("postCount", postCount);
        model.addAttribute("commentCount", commentCount);
         model.addAttribute("likesCount", likesCount);
        List<PostDto> posts = postService.getAllPosts();
        Collections.reverse(posts);
        for (PostDto post : posts) {
        List<CommentDto> comments = commentService.getCommentsByPostId(post.getId());
        post.setComments(comments);
    }   
        List<Long> likedPostIds = postService.getLikedPostIdsForUser(userService.getUserDtoByUsername(loggedInUser).getUserId());
        model.addAttribute("likedPostIds", likedPostIds);
        model.addAttribute("posts", posts);
        return "hello";
    }



    
     
    
    
}
