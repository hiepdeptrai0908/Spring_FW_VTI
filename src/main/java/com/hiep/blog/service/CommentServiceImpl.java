package com.hiep.blog.service;

import com.hiep.blog.dto.CommentDto;
import com.hiep.blog.entity.Comment;
import com.hiep.blog.form.CommentCreateForm;
import com.hiep.blog.form.CommentFilterForm;
import com.hiep.blog.form.CommentUpdateForm;
import com.hiep.blog.mapper.CommentMapper;
import com.hiep.blog.repository.CommentRepository;
import com.hiep.blog.repository.PostRepository;
import com.hiep.blog.specifition.CommentSpecifition;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        var spec = CommentSpecifition.buildSpec(form);
        return commentRepository.findAll(spec, pageable).map(CommentMapper::map);
    }

    @Override
    public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable).map(CommentMapper::map);
    }

    @Override
    public CommentDto findById(Long id) {
        return commentRepository.findById(id)
                .map(CommentMapper::map)
                .orElse(null);
    }

    @Override
    public CommentDto create(Long postId, CommentCreateForm form) {
        var optional = postRepository.findById(postId);
        if (optional.isEmpty()) {
            return null;
        }
        var post = optional.get();
        var comment = CommentMapper.map(form);
        comment.setPost(post);
        var savedComment = commentRepository.save(comment);
        return CommentMapper.map(savedComment);
    }

    @Override
    public CommentDto update(Long id, CommentUpdateForm form) {
        var optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        var comment = optional.get();
        CommentMapper.map(comment, form);
        var updatedComment = commentRepository.save(comment);
        return CommentMapper.map(updatedComment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        commentRepository.deleteByEmail(email);
    }
}
