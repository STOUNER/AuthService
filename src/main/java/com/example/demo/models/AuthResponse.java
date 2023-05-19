package com.example.demo.models;

public class AuthResponse {
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    // геттеры и сеттеры

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}