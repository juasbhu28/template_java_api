package com.minmarket.template_java_api.controllers;

public class UserControllerTests {

    //@Test
    //public void getAll_WhenUsersExist_ShouldReturn200WithUserResponseDTOs() {
    //    new User();
    //    User user1 = User
    //            .builder()
    //            .id(1)
    //            .name("User 1")
    //            .build();
    //    User user2 = User
    //            .builder()
    //            .id(2)
    //            .name("User 2")
    //            .build();
    //    List<User> users = Arrays.asList(user1, user2);
//
    //    when(userRepository.findAll()).thenReturn(users);
//
    //    ResponseEntity<List<UserResponseDTO>> result = userService.getAll();
//
    //    assertEquals(HttpStatus.OK, result.getStatusCode());
    //    assertEquals(users.size(), result.getBody().size());
    //    assertEquals(user1.getId(), result.getBody().get(0).getUser().getId());
    //    assertEquals(user1.getName(), result.getBody().get(0).getUser().getName());
    //    assertEquals(user2.getId(), result.getBody().get(1).getUser().getId());
    //    assertEquals(user2.getName(), result.getBody().get(1).getUser().getName());
    //}
//
    //@Test
    //public void getAll_WhenNoUsersExist_ShouldReturn204() {
    //    when(userRepository.findAll()).thenReturn(null);
    //    ResponseEntity<List<UserResponseDTO>> result = userService.getAll();
    //    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    //}
//
    //@Test
    //public void testCreateUser_Success() {
    //    UserRequestDTO requestDTO = new UserRequestDTO();
    //    requestDTO.setName("Juan");
    //    requestDTO.setActive(true);
//
    //    new User();
    //    User user = User.builder()
    //            .name("Juan")
    //            .active(Boolean.TRUE)
    //            .build();
//
    //    when(userRepository.save(any(User.class))).thenReturn(user);
//
    //    ResponseEntity<UserResponseDTO> response = userService.createUser(requestDTO);
//
    //    assertEquals(HttpStatus.OK, response.getStatusCode());
    //    UserResponseDTO userResponseDTO = response.getBody();
    //    assertEquals("Juan", userResponseDTO.getUser().getName());
    //    assertEquals(true, userResponseDTO.getUser().getActive());
    //}
//
    //@Test
    //public void testCreateUser_Failure() {
    //    UserRequestDTO requestDTO = new UserRequestDTO();
    //    requestDTO.setName("Juan");
    //    requestDTO.setActive(true);
//
    //    when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());
    //    GeneralException exceptionExpected = new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error saving user");
    //    try{
    //        userService.createUser(requestDTO);
    //    }catch(GeneralException ex){
    //        assertEquals(exceptionExpected.getStatus(), ex.getStatus());
    //        assertTrue(exceptionExpected.getMessage().contains(ex.getMessage()));
    //    }
    //}
//
    //@Test
    //public void testGetOne_userFound() {
    //    Integer id = 1;
    //    new User();
    //    User user = User
    //            .builder()
    //            .id(id)
    //            .name("Juan")
    //            .build();
    //    UserResponseDTO userResponseDTO = new UserResponseDTO();
    //    userResponseDTO.setUser(user);
//
    //    when(userRepository.findFirstById(id)).thenReturn(userResponseDTO);
//
    //    ResponseEntity<UserResponseDTO> result = userService.getOne(id);
//
    //    assertEquals(HttpStatus.OK, result.getStatusCode());
    //    UserResponseDTO userResponse = result.getBody();
    //    assertEquals(id, Objects.requireNonNull(userResponse).getUser().getId());
    //}
//
    //@Test
    //public void testGetOne_userNotFound() {
    //    Integer id = 2;
    //    when(userRepository.findFirstById(id)).thenReturn(null);
    //    GeneralException exceptionExpected = new GeneralException(HttpStatus.NO_CONTENT.value(), "User not found with id: " + id);
    //    try{
    //        userService.getOne(id);
    //    }catch(GeneralException ex){
    //        assertEquals(exceptionExpected.getStatus(), ex.getStatus());
    //        assertTrue(exceptionExpected.getMessage().contains(ex.getMessage()));
    //    }
    //}
}
