package com.inventar.InventoryManagement.dto;

public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearerjgvvkhbjblknhljbllhhvcfjghvuklknjnihbkhbjklbnkjbhjvghchgvhvjhv";
    private Long userId;
    private String username;
    private String role;

    public AuthResponseDTO(String accessToken, Long userId, String username, String role) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public AuthResponseDTO(String accessToken, String username, String role) {
        this.accessToken = accessToken;
        this.username = username;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
