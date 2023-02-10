package com.minmarket.template_java_api.controllers;

import com.minmarket.template_java_api.constants.RouteMapping;
import com.minmarket.template_java_api.dtos.requests.UserRequestDTO;
import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;
import com.minmarket.template_java_api.exceptions.GeneralException;
import com.minmarket.template_java_api.services.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(RouteMapping.USER_API)
@Api(value="Operations pertaining to user in User Management System", tags = RouteMapping.USER_API)
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
        try{
            List<UserResponseDTO> userResponseDTOS = userService.getAll();
            return new ResponseEntity<>(userResponseDTOS, HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@RequestParam(name = "id") Integer id) {
        try {
            UserResponseDTO userResponseDTO = userService.getUserById(id);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch(GeneralException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.valueOf(e.getStatus()));
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO requestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.createUser(requestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch(GeneralException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.valueOf(e.getStatus()));
        }
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam(name = "id") Integer id, @RequestBody UserRequestDTO userRequest) {
        try{
            return ResponseEntity.ok(userService.updateUser(id, userRequest));
        }catch (GeneralException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.valueOf(e.getStatus()));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@RequestParam(name = "id")Integer id) {
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }catch (GeneralException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.valueOf(e.getStatus()));
        }
    }


}
