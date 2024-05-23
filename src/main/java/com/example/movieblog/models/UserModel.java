package com.example.movieblog.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private UserAuthority authority;

    public UserModel(UUID id, String username, String email, String password, String phone, UserAuthority authority) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.authority = authority;
    }

    public UserModel() {
    }

    public UserModel(String id, String username, String email, String password, String phone, UserAuthority authority) {
        this.id = UUID.fromString(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.authority = authority;
    }

    public UserModel(String username, String email, String password, String phone, UserAuthority authority) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.authority == UserAuthority.ADMIN) return List.of(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"));
        return List.of(new SimpleGrantedAuthority("user"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
