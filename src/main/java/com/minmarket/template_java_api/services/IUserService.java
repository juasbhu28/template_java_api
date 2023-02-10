package com.minmarket.template_java_api.services;

import com.minmarket.template_java_api.dtos.requests.UserRequestDTO;
import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;

import java.util.List;

public interface IUserService {
    public List<UserResponseDTO> getAll();
    public UserResponseDTO  getUserById(Integer id);
    UserResponseDTO createUser(UserRequestDTO requestDTO);
    public UserResponseDTO updateUser(Integer id, UserRequestDTO requestDTO);
    public void deleteUser(Integer id);


}
