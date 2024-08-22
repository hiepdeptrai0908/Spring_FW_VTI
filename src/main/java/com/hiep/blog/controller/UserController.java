package com.hiep.blog.controller;

import com.hiep.blog.dto.UserDto;
import com.hiep.blog.form.UserCreateForm;
import com.hiep.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/api/v1/users")
    public UserDto create(@RequestBody UserCreateForm form) {
        return userService.create(form);
    }
}
