package com.blog.service;

import com.blog.dtos.PostDto;
import com.blog.entity.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(int id);
}
