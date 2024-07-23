package com.hiep.blog.dto;

import com.hiep.blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostDto {
    private UUID id;

    private String title;

    private String content;

    private String description;

    private Post.Status status;

    private String createdAt;

    private String updatedAt;
}
