package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Role;
import com.dentcareplus.dentcareplusspringboot.entity.User;
import com.dentcareplus.dentcareplusspringboot.enums.RoleEnum;
import com.dentcareplus.dentcareplusspringboot.repository.RoleRepository;
import com.dentcareplus.dentcareplusspringboot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, RoleEnum roleEnum) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Fetch the role based on the RoleEnum
        Role role = roleRepository.findByName(roleEnum.name())
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);
    }
}