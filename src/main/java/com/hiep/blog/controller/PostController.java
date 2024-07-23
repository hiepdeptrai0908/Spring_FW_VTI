package com.hiep.blog.controller;

import com.hiep.blog.anotation.PostIdExists;
import com.hiep.blog.dto.PostDto;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostUpdateForm;
import com.hiep.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @GetMapping("/api/v1/posts")
    public Page<PostDto> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostDto findById(@PathVariable("id") @PostIdExists long id) {
        return postService.findById(id);
    }

    @PostMapping("/api/v1/posts")
    public PostDto create(@Valid @RequestBody PostCreateForm form) {
        return postService.create(form);
    }

    @PutMapping("/api/v1/posts/{id}")
    public PostDto update(@PathVariable("id") @PostIdExists long id,@Valid  @RequestBody PostUpdateForm form) {
        return postService.update(id, form);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void deleteById(@PathVariable("id") @PostIdExists long id) {
        postService.deleteById(id);
    }
}
