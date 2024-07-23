package com.hiep.blog.service;

import com.hiep.blog.dto.CommentDto;
import com.hiep.blog.form.CommentCreateForm;
import com.hiep.blog.form.CommentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    Page<CommentDto> findAll(Pageable pageable);
    Page<CommentDto> findByPostId(UUID postId, Pageable pageable);
    CommentDto findById(Long id);
    CommentDto create(UUID postId, CommentCreateForm form);
    CommentDto update(Long id, CommentUpdateForm form);
    void delete(Long id);
    void deleteByEmail(String email);
}
