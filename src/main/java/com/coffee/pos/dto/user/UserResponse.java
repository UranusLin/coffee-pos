package com.coffee.pos.dto.user;

import com.coffee.pos.model.UserPermission;
import com.coffee.pos.model.UserRole;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String email;
    private Set<UserRole> roles;
    private Set<UserPermission> permissions;
}
