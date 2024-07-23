package com.hiep.blog.mapper;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.entity.Post;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostUpdateForm;

public class PostMapper {

    // Ánh xạ form create về entity
    public static Post map(PostCreateForm form) {
        var post = new Post();
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setContent(form.getContent());
        post.setStatus(Post.Status.valueOf(form.getStatus()));
        return post;
    }

    // Ánh xạ dữ liệu entity trả về DTO (client)
    public static PostDto map(Post post) {
        var dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setStatus(post.getStatus());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());

        return dto;
    }

    // Ánh xạ form update
    public static void map(PostUpdateForm form, Post post) {
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setContent(form.getContent());
        post.setStatus(post.getStatus());
    }
}
