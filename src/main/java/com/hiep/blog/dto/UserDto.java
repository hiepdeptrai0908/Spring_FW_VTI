package com.hiep.blog.dto;

import com.hiep.blog.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private User.Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
