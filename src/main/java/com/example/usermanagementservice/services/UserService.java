package com.example.usermanagementservice.services;

import com.example.usermanagementservice.dtos.PageData;
import com.example.usermanagementservice.dtos.requests.UserDetailsDto;
import com.example.usermanagementservice.dtos.requests.UserProfileUpdateDto;
import com.example.usermanagementservice.dtos.requests.UserSignUpDto;
import com.example.usermanagementservice.dtos.requests.UserSigninDto;
import com.example.usermanagementservice.entities.User;

import java.util.List;

public interface UserService {
  String signup(UserSignUpDto signUpDetails);
  String signin(UserSigninDto loginDetails);
  User updateProfile(UserProfileUpdateDto userProfileUpdateDto, String userEmail);
  UserDetailsDto getUserProfile(String userEmail);

  List<UserDetailsDto> findAllUsers(PageData pageData);

UserDetailsDto getAnotherUserProfile(String email);

  int deleteMyProfile(String userEmail);
}
