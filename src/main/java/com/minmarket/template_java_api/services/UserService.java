package com.minmarket.template_java_api.services;

import com.minmarket.template_java_api.dtos.requests.UserRequestDTO;
import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;
import com.minmarket.template_java_api.entities.User;
import com.minmarket.template_java_api.exceptions.GeneralException;
import com.minmarket.template_java_api.repositories.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements IUserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        List<User> users = userRepository.findAll();
        if (users == null || users.isEmpty()) {
            throw new GeneralException(HttpStatus.NO_CONTENT.value(), "No users found");
        }
        return users.stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(HttpStatus.NO_CONTENT.value(), "User not found with id: " + id));
        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        try {
            User newUser = User.builder()
                    .name(requestDTO.getName())
                    .active(requestDTO.getActive())
                    .build();
            User savedUser = userRepository.save(newUser);
            return new UserResponseDTO(savedUser);
        } catch(Exception e) {
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed saving user " + requestDTO.getName() + " " + e.getMessage());
        }
    }

    @Override
    public UserResponseDTO updateUser(Integer id, UserRequestDTO requestDTO){
        return userRepository.findById(id)
            .map(user1 -> {
                new User();
                User user = User.builder()
                        .name(requestDTO.getName())
                        .active(requestDTO.getActive())
                        .build();
                return new UserResponseDTO(userRepository.save(user));
            })
            .orElseThrow(() -> new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User not found for id:" + id));
    }

    @Override
    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }else{
            throw new GeneralException(HttpStatus.NO_CONTENT.value(), "User not found with id: " + id);
        }
    }


}
