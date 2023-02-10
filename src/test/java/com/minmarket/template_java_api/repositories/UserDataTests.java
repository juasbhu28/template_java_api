package com.minmarket.template_java_api.repositories;

import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;
import com.minmarket.template_java_api.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class UserDataTests {
    @Autowired
    private IUserRepository userRepository;

    private User user;

    @Test
    @Rollback(value = false)
    void testCreateUser(){
        User user = User.builder()
                .name("Juan")
                .active(Boolean.TRUE)
                .build();
        User newUser = userRepository.save(user);
        assertNotNull(newUser);
    }

    @Test
    @Rollback()
    void testSearchUser(){
        String name = "Juan";
        User user = User.builder()
                .name("Juan")
                .active(Boolean.TRUE)
                .build();
        userRepository.save(user);
        User newUser = userRepository.findByName(name);
        assertThat(newUser.getName()).isEqualTo(name);
    }

    @Test
    @Rollback()
    void testSearchUserNotExist(){
        String name = "Rodrigo";
        User user = userRepository.findByName(name);
        assertNull(user);
    }

    @Test
    @Rollback()
    void testFindFirstById() {
        User user = User.builder()
                .name("Juan")
                .active(Boolean.TRUE)
                .build();
        user = userRepository.save(user);

        Optional<UserResponseDTO> response = userRepository.findFirstById(user.getId());
        assertNotNull(response);
        assertEquals(user.getId(), response.get().getUser().getId());
    }
}