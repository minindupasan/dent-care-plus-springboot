package com.dentcareplus.dentcareplusspringboot.config;

import com.dentcareplus.dentcareplusspringboot.entity.Role;
import com.dentcareplus.dentcareplusspringboot.enums.RoleEnum;
import com.dentcareplus.dentcareplusspringboot.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initializeRoles(RoleRepository roleRepository) {
        return args -> {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                roleRepository.findByName(roleEnum.name()).orElseGet(() -> {
                    Role role = new Role();
                    role.setName(roleEnum.name());
                    return roleRepository.save(role);
                });
            }
        };
    }
}