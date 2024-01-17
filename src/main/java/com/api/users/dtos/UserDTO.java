package com.api.users.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class UserDTO {


    @NotNull
    private  String firstName;

    @NotNull
    private String  lastName;

    @NotEmpty(message = "El campo es obligatorio")
    private String email;

    @NotBlank
    private  String address;

    @NotBlank

    private String departmentId;


}
