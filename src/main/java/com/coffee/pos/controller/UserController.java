package com.coffee.pos.controller;

import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.user.CreateUserDTO;
import com.coffee.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired private UserService userService;

    @PostMapping
    public ResponseEntity<CommonObjectResponse> create(@RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<CommonObjectResponse> getByName(@PathVariable String userName) {
        return userService.findUserByUserName(userName);
    }
}
