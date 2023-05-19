package com.example.demo.services;
import com.example.demo.models.AuthRequest;
import com.example.demo.models.User;
import com.example.demo.repositories.UserAuthRepository;
import com.example.demo.security.jwt.JwtUtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private JwtUtilClass jwtUtilClass;

    //--Сервис для нахождения пользователя по номеру телефона.
    public User getUserByPhoneNumber(AuthRequest authRequest) {
        return userAuthRepository.getReferenceById(authRequest.getUsername());
    }

    //--Сервич для создания токена для пользователя после получения пользователя с Service-lvl.
    public String getTokenByUser(String phoneNumber) {

        return jwtUtilClass.generateToken(userAuthRepository.getReferenceById(phoneNumber));

    }

    public Boolean getJwtTokenStatusByUser(String token, String phoneNumber){
        User user = userAuthRepository.getReferenceById(phoneNumber);
        return jwtUtilClass.validateToken(token, user);
    }


}