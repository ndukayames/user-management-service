package com.example.usermanagementservice.services.impl;

import com.example.usermanagementservice.configs.jwt.JwtUtils;
import com.example.usermanagementservice.dtos.UserProfileUpdateDto;
import com.example.usermanagementservice.dtos.UserSignUpDto;
import com.example.usermanagementservice.dtos.UserSigninDto;
import com.example.usermanagementservice.entities.User;
import com.example.usermanagementservice.exceptions.*;
import com.example.usermanagementservice.repositories.UserRepository;
import com.example.usermanagementservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private JwtUtils jwtUtils;
@Override
public String signup(UserSignUpDto signUpDetails) {
  // check if user exists
  Optional<User> userExist = userRepository.findByEmail(signUpDetails.getEmail());
  if(userExist.isPresent()) {
    throw new UserAlreadyExistException("This email belongs to someone else");
  }
  // validate and hash password
  if (!signUpDetails.getPassword1().equals(signUpDetails.getPassword2())) {
    throw new PasswordMismatchException("Password doesn't match");
  }
  // encode password
  String encodedPassword = passwordEncoder.encode(signUpDetails.getPassword1());
  User newUser = new User();
  newUser.setEmail(signUpDetails.getEmail());
  newUser.setFirstName(signUpDetails.getFirstName());
  newUser.setLastName(signUpDetails.getLastName());
  newUser.setPhoneNumber(signUpDetails.getPhoneNumber());
  newUser.setPassword(encodedPassword);
  newUser = userRepository.save(newUser);
  // verify if new user was created
  if(!newUser.getEmail().isBlank()) {
    return "Signup Successful";
  } else {
    throw new UnknownServerErrorException("For some reasons, your signup was not successful, please contact us or try again");
  }
}

@Override
public String signin(UserSigninDto loginDetails) {
  // check if user exists
  Optional<User> userExist = userRepository.findByEmail(loginDetails.getEmail());
  if(userExist.isEmpty()) {
    throw new UserDoesNotExistException("We can't find an account associated with this email");
  }
  // compare password
  if(!passwordEncoder.matches(loginDetails.getPassword(), userExist.get().getPassword())) {
    throw new IncorrectPasswordException("Incorrect password, please try again");
  }
  // all is good ... add extra validations here else return login token
  // generate token
  return jwtUtils.generateJwtToken(userExist.get().getEmail());
}

@Override
public User updateProfile(UserProfileUpdateDto newDetails, String userEmail) {
  // get existing account details
  User existingUser = userRepository.findByEmail(userEmail).get();
  
  // now check for valid fields and add to existing user
  if(newDetails.getEmail() != null && !newDetails.getEmail().isBlank() ) {
    existingUser.setEmail(newDetails.getEmail());
  }
  if(newDetails.getFirstName() != null &&!newDetails.getFirstName().isBlank()) {
    existingUser.setFirstName(newDetails.getFirstName());
  }
  if(newDetails.getLastName() != null && !newDetails.getLastName().isBlank()) {
    existingUser.setLastName(newDetails.getLastName());
  }
  if(newDetails.getPhoneNumber() != null && !newDetails.getPhoneNumber().isBlank()) {
    existingUser.setPhoneNumber(newDetails.getPhoneNumber());
  }
  
  // set password
  if(newDetails.getPassword1() != null && newDetails.getPassword2() != null && !newDetails.getPassword2().isBlank() && !newDetails.getPassword1().isBlank()) {
    if(newDetails.getPassword1().equals(newDetails.getPassword2())) {
      existingUser.setPassword(passwordEncoder.encode(newDetails.getPassword1()));
    } else {
      throw new PasswordMismatchException("Your new password doesn't match");
    }
  }
  
  
  User newProfileDetails = userRepository.save(existingUser);
  return  newProfileDetails;
}
}
