package com.thinplt.springboot1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address shout be valid")
    private String email;
}
