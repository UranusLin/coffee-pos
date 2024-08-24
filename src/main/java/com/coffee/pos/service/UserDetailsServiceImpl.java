package com.coffee.pos.service;

import com.coffee.pos.model.User;
import com.coffee.pos.repository.UserRepository;
import com.coffee.pos.repository.UserRoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Autowired private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //        User user = userRepository.findByEmail(email);
        //        if (user == null) {
        //            throw new UsernameNotFoundException("User not found");
        //        }

        //        List<UserRole> userRoleList = userRoleRepository.findByUser(user)
        //                .orElseThrow(() -> new UsernameNotFoundException("User not found any
        // roles"));
        //        System.out.println(userRoleList);

        //        List<GrantedAuthority> authorities = userRoleList.stream()
        //                .map(role -> new SimpleGrantedAuthority("ROLE_" +
        // role.getRole().getName().toUpperCase()))
        //                .collect(Collectors.toList());
        //
        // ROLE_USER, ROLE_ADMIN, ROLE_MANAGER
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }
}
