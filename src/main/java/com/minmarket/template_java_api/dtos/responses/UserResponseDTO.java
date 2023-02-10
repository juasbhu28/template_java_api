package com.minmarket.template_java_api.dtos.responses;


import com.fasterxml.jackson.annotation.JsonView;
import com.minmarket.template_java_api.config.ViewsConfig;
import com.minmarket.template_java_api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    @JsonView({ViewsConfig.User.class})
    private User user;

}
