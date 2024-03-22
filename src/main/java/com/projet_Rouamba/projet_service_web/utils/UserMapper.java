package com.projet_Rouamba.projet_service_web.utils;



import com.projet_Rouamba.projet_service_web.domain.UserEntity;
import com.projet_Rouamba.projet_service_web.dto.UserDTO;

public class UserMapper {

    private UserMapper() {
        // Private constructor to prevent instantiation
    }

    public static UserDTO fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        // Not mapping the password for security reasons
        return userDTO;
    }

    public static UserEntity fromDTO(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setRole(userDTO.getRole());
        // Not mapping the password for security reasons
        return userEntity;
    }
}
