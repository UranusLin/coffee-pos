package com.coffee.pos.repository;

import com.coffee.pos.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    //    User findByEmail(String email);

    Optional<User> findByEmail(String email);

    //    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
