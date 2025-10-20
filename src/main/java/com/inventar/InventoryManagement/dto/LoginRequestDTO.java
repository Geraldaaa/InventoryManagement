package com.inventar.InventoryManagement.dto;

public class LoginRequestDTO {
    private String username;
    private String password;
    private String role; // 🔹 Roli i përdoruesit (ROLE_ADMIN, ROLE_USER, ROLE_MANAGER)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
