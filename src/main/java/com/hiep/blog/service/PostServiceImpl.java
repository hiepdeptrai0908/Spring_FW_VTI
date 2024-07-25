package com.hiep.blog.service;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostFilterForm;
import com.hiep.blog.form.PostUpdateForm;
import com.hiep.blog.mapper.PostMapper;
import com.hiep.blog.repository.PostRepository;
import com.hiep.blog.specifition.PostSpecifition;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository repository;

    @Override
    public Page<PostDto> findAll(PostFilterForm form, Pageable pageable) {
        var spec = PostSpecifition.buildSpec(form);
        return repository.findAll(spec, pageable).map(PostMapper::map);
    }

    @Override
    public PostDto findById(long id) {
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
    public PostDto update(long id, PostUpdateForm form) {
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
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
