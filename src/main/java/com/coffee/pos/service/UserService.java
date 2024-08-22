package com.coffee.pos.service;

import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.user.LoginRequestDTO;
import com.coffee.pos.dto.user.RegisterRequestDTO;
import com.coffee.pos.dto.user.UserResponse;
import com.coffee.pos.enums.CommonStatus;
import com.coffee.pos.model.User;
import com.coffee.pos.repository.RoleRepository;
import com.coffee.pos.repository.UserRepository;
import com.coffee.pos.utils.ResponseUtil;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired private UserRepository userRepository;
    @Autowired private ResponseUtil responseUtil;
    @Autowired private RoleRepository roleRepository;
    @Autowired PasswordEncoder passwordEncoder;

    //    public ResponseEntity<CommonObjectResponse> createUser(CreateUserDTO createUserDTO) {
    //        User user =
    //                User.builder()
    //                        .username(createUserDTO.getUserName())
    //                        .email(createUserDTO.getEmail())
    //                        .createdAt(LocalDateTime.now())
    //                        .build();
    //        userRepository.save(user);
    //        return responseUtil.getObjectResponse("Success", CommonStatus.SUCCESS, user);
    //    }
    //
    //    public ResponseEntity<CommonObjectResponse> findUserByUserName(String username) {
    //        User user = userRepository.findByUsername(username);
    //        if (user != null) {
    //            CommonObjectResponse commonObjectResponse =
    //                    new CommonObjectResponse("Success", CommonStatus.SUCCESS, user);
    //            return new ResponseEntity<>(commonObjectResponse, HttpStatus.OK);
    //        } else {
    //            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //        }
    //    }

    @Transactional
    public ResponseEntity<CommonObjectResponse> registerNewUser(
            RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByUsername(registerRequestDTO.getUserName())) {
            CommonObjectResponse response =
                    new CommonObjectResponse("User name exist", CommonStatus.FAILED, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            CommonObjectResponse response =
                    new CommonObjectResponse("Email exist", CommonStatus.FAILED, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user =
                User.builder()
                        .username(registerRequestDTO.getUserName())
                        .email(registerRequestDTO.getEmail())
                        .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                        .createdAt(LocalDateTime.now())
                        .build();

        //        Role userRole =
        // roleRepository.findByNameIgnoreCaseContaining("ROLE_USER").orElseThrow(() ->
        //                new RuntimeException("Error: Role is not found."));
        //        List<Role> roleList = roleRepository.findAll();
        //        logger.info(com.coffee.pos.enums.Role.ROLE_USER.name(), userRole);
        //        user.addRole(userRole);
        userRepository.save(user);

        UserResponse userResponse =
                UserResponse.builder()
                        .id(user.getId())
                        .userName(user.getUsername())
                        .email(user.getEmail())
                        //                        .roles(user.getUserRoles())
                        //                        .permissions(user.getUserPermissions())
                        .build();
        CommonObjectResponse response =
                new CommonObjectResponse("Success", CommonStatus.SUCCESS, userResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<CommonObjectResponse> userLoginService(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
                UserResponse userResponse =
                        UserResponse.builder()
                                .id(user.getId())
                                .userName(user.getUsername())
                                .email(user.getEmail())
                                .build();
                CommonObjectResponse response =
                        new CommonObjectResponse("Success", CommonStatus.SUCCESS, userResponse);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                CommonObjectResponse response =
                        new CommonObjectResponse("User not exist", CommonStatus.FAILED, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            CommonObjectResponse response =
                    new CommonObjectResponse("User not exist", CommonStatus.FAILED, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
