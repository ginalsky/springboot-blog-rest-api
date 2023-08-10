package com.blog.controller;


import com.blog.dtos.PostDto;
import com.blog.entity.Post;
import com.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create a post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all posts rest api
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }
    //get post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") int id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //delete post by id rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") int id){

        postService.deletePost(id);

        return new ResponseEntity<>("Post entity deleted successfully!", HttpStatus.OK);
    }
}
