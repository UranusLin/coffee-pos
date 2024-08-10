package com.coffee.pos.service;

import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.user.CreateUserDTO;
import com.coffee.pos.enums.CommonStatus;
import com.coffee.pos.model.User;
import com.coffee.pos.repository.UserRepository;
import com.coffee.pos.utils.ResponseUtil;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private ResponseUtil responseUtil;

    public ResponseEntity<CommonObjectResponse> createUser(CreateUserDTO createUserDTO) {
        User user =
                User.builder()
                        .username(createUserDTO.getUserName())
                        .email(createUserDTO.getEmail())
                        .createdAt(LocalDateTime.now())
                        .build();
        userRepository.save(user);
        return responseUtil.getObjectResponse("Success", CommonStatus.SUCCESS, user);
    }

    public ResponseEntity<CommonObjectResponse> findUserByUserName(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            CommonObjectResponse commonObjectResponse =
                    new CommonObjectResponse("Success", CommonStatus.SUCCESS, user);
            return new ResponseEntity<>(commonObjectResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
