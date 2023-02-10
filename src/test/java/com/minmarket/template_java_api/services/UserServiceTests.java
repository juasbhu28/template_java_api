package com.minmarket.template_java_api.services;

import com.minmarket.template_java_api.dtos.requests.UserRequestDTO;
import com.minmarket.template_java_api.dtos.responses.UserResponseDTO;
import com.minmarket.template_java_api.entities.User;
import com.minmarket.template_java_api.exceptions.GeneralException;
import com.minmarket.template_java_api.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


//Prueba de creación de cliente: Crea un nuevo cliente utilizando el servicio de creación y verifica que se ha creado correctamente en la base de datos.
//Prueba de lectura de cliente: Obtén un cliente existente utilizando el metodo que recibe un id devuelve un objeto user y verifica que se ha obtenido correctamente.
//Prueba de actualización de cliente: Actualiza un cliente existente utilizando el endpoint de actualización y verifica que se ha actualizado correctamente en la base de datos.
//Prueba de eliminación de cliente: Elimina un cliente existente utilizando el endpoint de eliminación y verifica que se ha eliminado correctamente de la base de datos.
//Prueba de validación de cliente: Crea un nuevo cliente con campos obligatorios vacíos y verifica que se ha recibido un error de validación

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private IUserService userService = new UserService(userRepository);

    @Autowired
    private IUserService userDependency;

    @Test
    void testGetAll_Success() {

        User user1 = User.builder()
                .id(1)
                .name("User1")
                .active(true)
                .build();

        User user2 = User.builder()
                .id(2)
                .name("User2")
                .active(false)
                .build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponseDTO> response = userService.getAll();

        assertNotNull(response);
        assertEquals(2, response.size());

        UserResponseDTO responseDTO1 = response.get(0);
        assertEquals(1, responseDTO1.getUser().getId().intValue());
        assertEquals("User1", responseDTO1.getUser().getName());
        assertEquals(true, responseDTO1.getUser().getActive());

        UserResponseDTO responseDTO2 = response.get(1);
        assertEquals(2, responseDTO2.getUser().getId().intValue());
        assertEquals("User2", responseDTO2.getUser().getName());
        assertEquals(false, responseDTO2.getUser().getActive());
    }

    @Test
     void testGetAll_NoUsersFound() {
        when(userRepository.findAll()).thenReturn(null);
        assertThrows(GeneralException.class, () -> userService.getAll());
    }


    @Test
     void getUserById_UserFound_ReturnUser() {
        User user = User.builder().id(1).name("User1").active(true).build();
        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.of(user));

        UserResponseDTO userResponseDTO = userService.getUserById(1);

        assertEquals(user.getId(), userResponseDTO.getUser().getId());
        assertEquals(user.getName(), userResponseDTO.getUser().getName());
        assertEquals(user.getActive(), userResponseDTO.getUser().getActive());
    }

    @Test
    void getUserById_UserNotFound_ThrowException() {
        Integer id = 2;
        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());
        assertThrows(GeneralException.class, () ->  userService.getUserById(id));
    }

    @Test
    void testCreateUser_Success() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("User1");
        requestDTO.setActive(true);

        User user = User.builder()
                .name("User1")
                .active(true)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO responseDTO = userService.createUser(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(requestDTO.getName(), responseDTO.getUser().getName());
        assertEquals(requestDTO.getActive(), responseDTO.getUser().getActive());
    }

    @Test
    void testCreateUser_WithException() {
        UserRequestDTO requestDTO = new UserRequestDTO(null, true);

        GeneralException exception = assertThrows(GeneralException.class, () -> userService.createUser(requestDTO));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatus());
        assertTrue(exception.getMessage().contains("Failed saving user "));
    }

    @Test
    void updateUserTest() {
        Integer id = 1;
        UserRequestDTO requestDTO = new UserRequestDTO("User 1", true);

        User user = User.builder()
                .id(id)
                .name("User 1")
                .active(true)
                .createDate(new Date())
                .updatedDateTime(LocalDateTime.now())
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO responseDTO = userService.updateUser(id, requestDTO);

        assertNotNull(responseDTO);
        assertEquals("User 1", responseDTO.getUser().getName());
        assertTrue(responseDTO.getUser().getActive());

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void updateUserNotFoundTest() {
        Integer id = 1;
        UserRequestDTO requestDTO = new UserRequestDTO("User 1", true);

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(GeneralException.class, () -> userService.updateUser(id, requestDTO));

        assertNotNull(exception);
        assertEquals("User not found for id:" + id, exception.getMessage());

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(0)).save(any(User.class));
    }
    @Test
    void deleteUser_WhenUserExists_ShouldDeleteSuccessfully() {
        // Given
        Integer id = 1;

        User user = User.builder()
                .id(id)
                .name("User1")
                .active(true)
                .build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // When
        userService.deleteUser(id);

        // Then
        verify(userRepository).deleteById(id);
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ShouldThrowException() {
        // Given
        Integer invalidId = -1;

        // when
        assertThrows(GeneralException.class, () -> userService.deleteUser(invalidId));

    }

}