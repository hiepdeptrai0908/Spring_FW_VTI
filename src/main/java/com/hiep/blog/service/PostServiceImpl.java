package com.hiep.blog.service;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostUpdateForm;
import com.hiep.blog.mapper.PostMapper;
import com.hiep.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository repository;

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(PostMapper::map);
    }

    @Override
    public PostDto findById(UUID id) {
        return repository.findById(id)
                .map(PostMapper::map)
                .orElse(null);
        // lambda
        // method reference
    }

    @Override
    public PostDto create(PostCreateForm form) {
        var post = PostMapper.map(form);
        var savePost = repository.save(post);
        return PostMapper.map(savePost);
    }

    @Override
    public PostDto update(UUID id, PostUpdateForm form) {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        var post = optional.get();
        PostMapper.map(form, post);
        var savedPost = repository.save(post);
        return PostMapper.map(savedPost);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
