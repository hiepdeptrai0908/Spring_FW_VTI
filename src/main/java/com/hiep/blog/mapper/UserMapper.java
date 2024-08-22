package com.hiep.blog.mapper;

import com.hiep.blog.dto.UserDto;
import com.hiep.blog.entity.User;
import com.hiep.blog.form.UserCreateForm;

public class UserMapper {
    public static User map(UserCreateForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return user;
    }

    public static UserDto map(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
