package com.hiep.blog.service;

import com.hiep.blog.dto.UserDto;
import com.hiep.blog.entity.User;
import com.hiep.blog.form.UserCreateForm;
import com.hiep.blog.mapper.UserMapper;
import com.hiep.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserDto create(UserCreateForm form) {
        var user = UserMapper.map(form);

        // Mã hoá mật khẩu
        var encodedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(User.Role.EMPLOYEE);
        var savedUser = userRepository.save(user);
        return UserMapper.map(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(), AuthorityUtils.NO_AUTHORITIES
        );
    }
}
