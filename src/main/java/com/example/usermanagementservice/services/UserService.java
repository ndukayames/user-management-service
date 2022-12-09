package com.example.usermanagementservice.services;

import com.example.usermanagementservice.dtos.UserProfileUpdateDto;
import com.example.usermanagementservice.dtos.UserSignUpDto;
import com.example.usermanagementservice.dtos.UserSigninDto;
import com.example.usermanagementservice.entities.User;
import com.example.usermanagementservice.exceptions.UserAlreadyExistException;

import java.security.Principal;

public interface UserService {
  String signup(UserSignUpDto signUpDetails);
  String signin(UserSigninDto loginDetails);
  User updateProfile(UserProfileUpdateDto userProfileUpdateDto, String userEmail);
}
