package com.minmarket.template_java_api.dtos.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO  {
    private String name;
    private Boolean active;

}
