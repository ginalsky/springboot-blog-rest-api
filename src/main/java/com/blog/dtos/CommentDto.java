package com.blog.dtos;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String name;
    private String email;
    private String body;
}
