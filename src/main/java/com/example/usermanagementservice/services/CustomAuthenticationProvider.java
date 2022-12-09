package com.example.usermanagementservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        logger.info("loading user with {} {}", username, password);
        UserDetails user = customUserDetailsService.loadUserByUsername(username);
        return checkPassword(user,password);
    }

    private Authentication checkPassword(UserDetails user, String rawPassword) {
        if(passwordEncoder.matches(rawPassword, user.getPassword())) {
            logger.info("User authentication successful");
            return new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities());
        }
        else {
            logger.info("User authentication unsuccessful");
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
