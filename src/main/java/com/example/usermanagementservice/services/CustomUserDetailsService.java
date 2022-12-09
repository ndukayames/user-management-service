package com.example.usermanagementservice.services;


import com.example.usermanagementservice.entities.User;
import com.example.usermanagementservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // find user by username or email
        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No User Found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities()
        );
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(){
        return new HashSet<GrantedAuthority>();
    }
}
