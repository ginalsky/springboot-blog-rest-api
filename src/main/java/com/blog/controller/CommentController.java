package com.blog.controller;

import com.blog.dtos.CommentDto;
import com.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") int postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsById(@PathVariable(name = "postId") int postID){
        return commentService.getAllCommentsByPostId(postID);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") int postId,
                                                     @PathVariable(name = "commentId") int commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @PutMapping("/posts/{postId}/comments/{commentId")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") int postId,
                                                    @PathVariable(name = "commentId") int commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateCommentById(postId, commentId, commentDto);

        return ResponseEntity.ok(updatedComment);
    }
}
