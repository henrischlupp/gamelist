package com.gamelist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "appusers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "resettoken")
    private String resetToken;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role = "USER";



    
    public User() {
    }

    

    public User(@Email @NotBlank @Size(max = 150) String email, @NotBlank @Size(max = 100) String username,
            @NotBlank String passwordHash, String role) {
        super();
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPasswordHash() {
        return passwordHash;
    }



    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }



    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "";
    }
}
