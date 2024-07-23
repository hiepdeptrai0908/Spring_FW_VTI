package com.hiep.blog.controller;

import com.hiep.blog.dto.CommentDto;
import com.hiep.blog.form.CommentCreateForm;
import com.hiep.blog.form.CommentUpdateForm;
import com.hiep.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(Pageable pageable) {
        return commentService.findAll(pageable);
    }
    @GetMapping("/api/v1/comments/posts/{postId}")
    public Page<CommentDto> findByPostId(@PathVariable("postId") Long postId, Pageable pageable) {
        return commentService.findByPostId(postId, pageable);
    }

    @GetMapping("/api/v1/comments/{id}")
    public CommentDto findById(@PathVariable("id") Long id) {
        return commentService.findById(id);
    }

    @PostMapping("/api/v1/posts/{postId}/comments")
    public CommentDto create(@PathVariable("postId") Long postId, @RequestBody CommentCreateForm form) {
        return commentService.create(postId, form);
    }

    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(@PathVariable("id") Long id, @RequestBody CommentUpdateForm form) {
        return commentService.update(id, form);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }

    @DeleteMapping("/api/v1/comments/email/{email}")
    public void deleteByEmail(@PathVariable("email") String email) {
        commentService.deleteByEmail(email);
    }

}
