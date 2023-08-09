package com.blog.service.impl;

import com.blog.dtos.PostDto;
import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }


    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    //convert entity to DTO
    private PostDto mapToDTO(Post Post) {
        PostDto postDto = new PostDto();
        postDto.setId(Post.getId());
        postDto.setTitle(Post.getTitle());
        postDto.setDescription(Post.getDescription());
        postDto.setContent(Post.getContent());

        return postDto;
    }
    //convert DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
