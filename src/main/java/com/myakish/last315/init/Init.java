package com.myakish.last315.init;

import com.myakish.last315.models.Role;
import com.myakish.last315.models.User;
import com.myakish.last315.repositories.RoleRepository;
import com.myakish.last315.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;


@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        List<Role> userRoles = Arrays.asList(userRole);
        List<Role> adminRoles = Arrays.asList(adminRole, userRole);

        User admin = new User();
        admin.setUsername("kolya");
        admin.setLastname("Kozhemyakin");
        admin.setAge((byte) 20);
        admin.setEmail("kolya@mail.ru");
        admin.setPassword(passwordEncoder.encode("100"));
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        User user = new User();
        user.setUsername("symon");
        user.setLastname("Kozhemyakin");
        user.setAge((byte) 15);
        user.setEmail("symon@mail.ru");
        user.setPassword(passwordEncoder.encode("100"));
        user.setRoles(userRoles);
        userRepository.save(user);
    }
}