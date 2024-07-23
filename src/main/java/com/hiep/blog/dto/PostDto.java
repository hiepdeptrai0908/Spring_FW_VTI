package com.hiep.blog.dto;

import com.hiep.blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private long id;

    private String title;

    private String content;

    private String description;

    private Post.Status status;

    private String createdAt;

    private String updatedAt;
}
