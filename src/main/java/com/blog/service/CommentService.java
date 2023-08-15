package com.blog.service;

import com.blog.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(int postID, CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(int postID);
}
