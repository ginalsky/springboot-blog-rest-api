package com.blog.service;

import com.blog.dtos.CommentDto;

public interface CommentService {
    CommentDto createComment(int postID, CommentDto commentDto);
}
