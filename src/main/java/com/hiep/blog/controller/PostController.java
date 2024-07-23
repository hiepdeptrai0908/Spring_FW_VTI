package com.hiep.blog.controller;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostUpdateForm;
import com.hiep.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @GetMapping("/api/v1/posts")
    public Page<PostDto> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostDto findById(@PathVariable("id") UUID id) {
        return postService.findById(id);
    }

    @PostMapping("/api/v1/posts")
    public PostDto create(@RequestBody PostCreateForm form) {
        return postService.create(form);
    }

    @PutMapping("/api/v1/posts/{id}")
    public PostDto update(@PathVariable("id") UUID id, @RequestBody PostUpdateForm form) {
        return postService.update(id, form);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        postService.deleteById(id);
    }
}
