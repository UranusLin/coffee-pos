package com.coffee.pos.controller;

import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.user.LoginRequestDTO;
import com.coffee.pos.dto.user.RegisterRequestDTO;
import com.coffee.pos.dto.user.UserResponse;
import com.coffee.pos.model.User;
import com.coffee.pos.repository.UserRepository;
import com.coffee.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    //    @PostMapping
    //    public ResponseEntity<CommonObjectResponse> create(@RequestBody RegisterRequestDTO
    // createUserDTO) {
    //        return userService.createUser(createUserDTO);
    //    }
    //
    //    @GetMapping("/{userName}")
    //    public ResponseEntity<CommonObjectResponse> getByName(@PathVariable String userName) {
    //        return userService.findUserByUserName(userName);
    //    }

    @PostMapping("/register")
    public ResponseEntity<CommonObjectResponse> registerUser(
            @RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.registerNewUser(registerRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonObjectResponse> userLogin(
            @RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.userLoginService(loginRequestDTO);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        UserResponse userResponse =
                UserResponse.builder()
                        .userName(user.getUsername())
                        .id(user.getId())
                        .email(user.getEmail())
                        .build();
        return ResponseEntity.ok(userResponse);
    }
}
