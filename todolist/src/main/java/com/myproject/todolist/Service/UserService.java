package com.myproject.todolist.Service;

import com.myproject.todolist.Dto.UserDto;
import com.myproject.todolist.Models.User;
import com.myproject.todolist.Payload.LoginRequest;
import com.myproject.todolist.Repository.UserRepository;
import com.myproject.todolist.Security.Jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    public ResponseEntity<String> register(UserDto userDto){
        if(!userRepository.existsByUsername(userDto.getUsername())){
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(
                    jwtUtils.generateJwtTokenFromUsername(user.getUsername()),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Username is already existed",HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null){
            return new ResponseEntity<>("Username or password incorrectly",HttpStatus.BAD_REQUEST);
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return new ResponseEntity<>("Username or password incorrectly",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                jwtUtils.generateJwtTokenFromUsername(user.getUsername()),
                HttpStatus.OK);
    }
}
