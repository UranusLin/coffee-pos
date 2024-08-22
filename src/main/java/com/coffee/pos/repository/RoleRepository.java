package com.coffee.pos.repository;

import com.coffee.pos.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    Optional<Role> findByNameIgnoreCaseContaining(@Param("name") String name);
}
