package com.hiep.blog.service;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Page<PostDto> findAll(Pageable pageable);

    PostDto findById(long id);

    PostDto create(PostCreateForm form);

    PostDto update(long id, PostUpdateForm form);

    void deleteById(long id);
}
