package com.api.users.mappers;

import com.api.users.dtos.UserDTO;
import com.api.users.entities.User;
import org.mapstruct.*;



import java.util.List;



@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface UserMapper {

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "department.id", target = "departmentId")

    UserDTO toUserDTO(User user);
    @InheritInverseConfiguration
    User toUser(UserDTO userDTO);

    List<UserDTO> userToUsersDTOs(List<User> user);

    User updateUserFromUserDTO(UserDTO userDTO, @MappingTarget User user);
}
