package com.hiep.blog.converter;

import com.hiep.blog.entity.Post;
import jakarta.persistence.AttributeConverter;

public class PostStatusConverter implements AttributeConverter<Post.Status, Character> {
    @Override
    public Character convertToDatabaseColumn(Post.Status status) {
        return status.toString().charAt(0);
    }

    @Override
    public Post.Status convertToEntityAttribute(Character code) {
        if (code.toString().equals("O")) {
            return Post.Status.OPENING;
        }
        if (code.toString().equals("C")) {
            return Post.Status.CLOSED;
        }

        throw new IllegalArgumentException(code.toString());
    }
}
