package com.coffee.pos.dto.user;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String userName;
    private String email;
    private String password;
}
