package com.coffee.pos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String password;

    //    @JsonIgnoreProperties("user")
    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //    private Set<UserRole> userRoles = new HashSet<>();
    //
    //    @JsonIgnoreProperties("user")
    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //    private Set<UserPermission> userPermissions = new HashSet<>();

    public void addRole(Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(this);
        userRole.setRole(role);
        //        userRoles.add(userRole);
    }

    public void addPermission(Permission permission) {
        UserPermission userPermission = new UserPermission();
        userPermission.setUser(this);
        userPermission.setPermission(permission);
        //        userPermissions.add(userPermission);
    }
}
