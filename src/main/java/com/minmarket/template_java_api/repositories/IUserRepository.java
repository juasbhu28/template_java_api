package com.minmarket.template_java_api.repositories;

import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;
import com.minmarket.template_java_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
    public Optional<UserResponseDTO> findFirstById(Integer id);

}