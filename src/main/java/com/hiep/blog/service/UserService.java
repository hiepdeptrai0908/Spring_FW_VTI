package com.hiep.blog.service;

import com.hiep.blog.dto.UserDto;
import com.hiep.blog.form.UserCreateForm;

public interface UserService {
    UserDto create(UserCreateForm form);
}
