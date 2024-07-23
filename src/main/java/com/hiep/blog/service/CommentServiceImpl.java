package com.hiep.blog.service;

import com.hiep.blog.dto.CommentDto;
import com.hiep.blog.entity.Comment;
import com.hiep.blog.form.CommentCreateForm;
import com.hiep.blog.form.CommentUpdateForm;
import com.hiep.blog.repository.CommentRepository;
import com.hiep.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public Page<CommentDto> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable).map(comment -> modelMapper
                .map(comment, CommentDto.class));
    }

    @Override
    public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable).map(comment -> modelMapper.map(comment, CommentDto.class));
    }

    @Override
    public CommentDto findById(Long id) {
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElse(null);
    }

    @Override
    public CommentDto create(Long postId, CommentCreateForm form) {
        var optional = postRepository.findById(postId);
        if (optional.isEmpty()) {
            return null;
        }
        var post = optional.get();
        var comment = modelMapper.map(form, Comment.class);
        comment.setPost(post);
        var savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public CommentDto update(Long id, CommentUpdateForm form) {
        var optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        var comment = optional.get();
        modelMapper.map(comment, form);
        var updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment, CommentDto.class);
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
