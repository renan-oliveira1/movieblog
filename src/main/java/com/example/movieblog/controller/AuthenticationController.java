package com.example.movieblog.controller;

import com.example.movieblog.dtos.AuthenticatedUserDto;
import com.example.movieblog.dtos.AuthenticationDto;
import com.example.movieblog.dtos.UserRegisterDto;
import com.example.movieblog.infra.security.TokenService;
import com.example.movieblog.models.UserAuthority;
import com.example.movieblog.models.UserModel;
import com.example.movieblog.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated AuthenticationDto data){
        var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticatedUserDto(data.email(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Validated UserRegisterDto userRegisterDto){
        if(userRepository.findByEmail(userRegisterDto.email()) != null) return ResponseEntity.badRequest().build();

        String encrytedPassword = new BCryptPasswordEncoder().encode(userRegisterDto.password());
        UserModel user = new UserModel(userRegisterDto.username(), userRegisterDto.email(), encrytedPassword, userRegisterDto.phone(), userRegisterDto.authority());
        UserModel savedUser = this.userRepository.save(user);
        var token = tokenService.generateToken(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticatedUserDto(savedUser.getEmail(), token));
    }
}
