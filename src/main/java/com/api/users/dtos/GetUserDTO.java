package com.api.users.dtos;


import com.api.users.entities.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetUserDTO {

    private  String firstName;

    private String  lastName;

    private String email;

    private  String address;

    private Department department;
}
