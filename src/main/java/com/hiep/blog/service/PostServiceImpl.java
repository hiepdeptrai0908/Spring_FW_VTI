package com.hiep.blog.service;

import com.hiep.blog.dto.PostDto;
import com.hiep.blog.entity.Post;
import com.hiep.blog.form.PostCreateForm;
import com.hiep.blog.form.PostFilterForm;
import com.hiep.blog.form.PostUpdateForm;
import com.hiep.blog.repository.PostRepository;
import com.hiep.blog.specification.PostSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository repository;
    private ModelMapper modelMapper;

    @Override
    public Page<PostDto> findAll(PostFilterForm form, Pageable pageable) {
        var spec = PostSpecification.buildSpec(form);
        return repository.findAll(spec, pageable).map(post -> modelMapper.map(post, PostDto.class));
    }

    @Override
    public PostDto findById(long id) {
        return repository.findById(id)
                .map(post ->
                        modelMapper
                        .map(post, PostDto.class))
                .orElse(null);
        // lambda
        // method reference
    }

    @Override
    public PostDto create(PostCreateForm form) {
        var post = modelMapper.map(form, Post.class);
        var savePost = repository.save(post);
        return modelMapper.map(savePost, PostDto.class);
    }

    @Override
    public PostDto update(long id, PostUpdateForm form) {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        var post = optional.get();
        modelMapper.map(form, post);
        var savedPost = repository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
