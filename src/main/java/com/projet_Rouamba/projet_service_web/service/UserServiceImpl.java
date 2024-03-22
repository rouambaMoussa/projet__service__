package com.projet_Rouamba.projet_service_web.service;

import com.projet_Rouamba.projet_service_web.dto.UserDTO;
import com.projet_Rouamba.projet_service_web.domain.UserEntity;
import com.projet_Rouamba.projet_service_web.repository.jpa.UserJpaRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class UserServiceImpl {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder; // Injection de BCryptPasswordEncoder

    public UserServiceImpl(UserJpaRepository userJpaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDTO userDTO) {
        UserEntity existingUser = userJpaRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("L'utilisateur existe déjà.");
        } else {
            UserEntity userEntity = mapDtoToEntity(userDTO);
            userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userJpaRepository.save(userEntity);
        }
    }





    public void deleteUser(Long userId) {
        userJpaRepository.deleteById(userId);
    }


    public UserDTO getUserById(Long userId) {
        Optional <UserEntity> userEntity = userJpaRepository.findById(userId);
        return mapEntityToDto(userEntity.get());
    }


    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userJpaRepository.findByEmail(email);
        return mapEntityToDto(userEntity);
    }


    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userJpaRepository.findAll();
        return userEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }



    public UserDTO mapEntityToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUsername(userEntity.getUsername());
        return userDTO;
    }

    public UserEntity mapDtoToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setRole(userDTO.getRole());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUsername(userDTO.getUsername());
        return userEntity;
    }
}
