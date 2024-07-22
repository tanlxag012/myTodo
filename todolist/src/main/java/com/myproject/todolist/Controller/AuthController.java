package com.myproject.todolist.Controller;

import com.myproject.todolist.Dto.UserDto;
import com.myproject.todolist.Payload.LoginRequest;
import com.myproject.todolist.Service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserService userService;
    @GetMapping("/auth/login")
    public String login(){
        return "login";
    }
    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
