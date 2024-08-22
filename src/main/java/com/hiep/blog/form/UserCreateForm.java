package com.hiep.blog.form;

import com.hiep.blog.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    private String name;

    private String username;

    private String email;

    private String password;
}
