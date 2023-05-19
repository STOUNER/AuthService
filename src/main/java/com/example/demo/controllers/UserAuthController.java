package com.example.demo.controllers;

import com.example.demo.models.AuthRequest;
import com.example.demo.models.AuthResponse;
import com.example.demo.models.User;
import com.example.demo.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        AuthResponse authResponse = new AuthResponse();
        User user = new User();
        try {
            //Проверяем, есть ли пользователь с таким номером телефона в БД.
            user = userAuthService.getUserByPhoneNumber(authRequest);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пользователь не найден, проверьте верно ли введён логин.");
            }

            if (user.getPassword() != authRequest.getPassword()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный пароль.");
            }


        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }

        String token = userAuthService.getTokenByUser(user.getPhoneNumber());
        authResponse.setToken(token);
        return ResponseEntity.ok(authResponse);

    }


}
