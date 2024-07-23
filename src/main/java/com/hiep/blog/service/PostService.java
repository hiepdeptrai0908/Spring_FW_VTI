package com.hiep.blog.service;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Page<PostDto> findAll(Pageable pageable);

    PostDto findById(UUID id);

    PostDto create(PostCreateForm form);

    PostDto update(UUID id, PostUpdateForm form);

    void deleteById(UUID id);
}
