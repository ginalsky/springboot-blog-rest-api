package com.blog.service;

import com.blog.dtos.PostDto;
import com.blog.dtos.PostResponse;
import com.blog.entity.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDirection);

    PostDto getPostById(int id);

    PostDto updatePost(PostDto postDto, int id);

    void deletePost(int id);
}
