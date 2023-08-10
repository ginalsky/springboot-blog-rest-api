package com.blog.service.impl;

import com.blog.dtos.PostDto;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {

        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> listOfPosts = posts.getContent();
        return listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }


    @Override
    public PostDto getPostById(int id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(int id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);
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
