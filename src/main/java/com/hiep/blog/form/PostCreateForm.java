package com.hiep.blog.form;

import com.hiep.blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateForm {
    private String title;

    private String content;

    private String description;

    private Post.Status status;
}
