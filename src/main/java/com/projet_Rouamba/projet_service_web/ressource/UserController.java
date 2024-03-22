package com.projet_Rouamba.projet_service_web.ressource;


import com.projet_Rouamba.projet_service_web.dto.UserDTO;
import com.projet_Rouamba.projet_service_web.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDTO userDTO) {
         userServiceImpl.registerUser(userDTO);
    }




}
