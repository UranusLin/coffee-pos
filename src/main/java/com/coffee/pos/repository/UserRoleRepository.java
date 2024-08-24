package com.coffee.pos.repository;

import com.coffee.pos.model.User;
import com.coffee.pos.model.UserRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<List<UserRole>> findByUser(User user);
}
