package com.minmarket.template_java_api.controllers;

import com.minmarket.template_java_api.dtos.requests.UserRequestDTO;
import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

public interface IUserController {
    @ApiOperation(
            value = "",
            notes = "This operation gets a list of available user",
            response = UserResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/")
    ResponseEntity<List<UserResponseDTO>> getAllUser();

    @ApiOperation(
            value = "",
            notes = "This operation return a user by Id",
            response = UserResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{id}")
    ResponseEntity<UserResponseDTO> getUserById(@RequestParam(name = "id") Integer id);

    @ApiOperation(
            value = "",
            notes = "This operation creates a user",
            response = UserResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("/")
    ResponseEntity<UserResponseDTO> createUser(
            @ApiParam(name = "UserRequestDTO", value = "This object contains the data to create a new user")
            UserRequestDTO requestDTO
    );

   @ApiOperation(
           value = "",
           notes = "This operation update data from a user",
           response = UserResponseDTO.class
   )
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Ok"),
           @ApiResponse(responseCode = "500", description = "Internal error")
   })
   @PatchMapping("/{id}")
   ResponseEntity<UserResponseDTO> updateUser(
           @ApiParam(name = "id", value = "This object contains the id data to update a user")
           @PathParam(value = "id")
           Integer id,
           @ApiParam(name = "UserRequestDTO", value = "This object contains de object to update")
           @RequestBody(required = true)
           UserRequestDTO userRequest
   );

    @ApiOperation(
            value = "",
            notes = "This operation delete a user",
            response = UserResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(
            @ApiParam(name = "id", value = "This object contains the id for delete user")
            @PathParam(value = "id")
            Integer id
    );



}
