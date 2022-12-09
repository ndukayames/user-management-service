package com.example.usermanagementservice.controllers;

import com.example.usermanagementservice.dtos.UserProfileUpdateDto;
import com.example.usermanagementservice.dtos.UserSignUpDto;
import com.example.usermanagementservice.dtos.UserSigninDto;
import com.example.usermanagementservice.dtos.responses.BaseResponse;
import com.example.usermanagementservice.dtos.responses.ResponseWithData;
import com.example.usermanagementservice.entities.User;
import com.example.usermanagementservice.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class UserController {

@Autowired
private UserService userService;

@PostMapping("auth/signup")
public ResponseEntity<BaseResponse> userSignUp(@Valid @RequestBody UserSignUpDto userSignUpDto) {
  
  String signupAction = userService.signup(userSignUpDto);
  BaseResponse baseResponse = new BaseResponse();
  baseResponse.setMessage(signupAction);
  baseResponse.setSuccess(true);
  
  return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
}

@PostMapping("auth/signin")
public ResponseEntity<BaseResponse> userSignIn(@Valid @RequestBody UserSigninDto userSigninDto) {
  String signinAction = userService.signin(userSigninDto);
  BaseResponse baseResponse = new BaseResponse();
  baseResponse.setMessage(signinAction);
  baseResponse.setSuccess(true);
  return new ResponseEntity<>(baseResponse, HttpStatus.OK);
}

  @PutMapping("users")
  public ResponseEntity<BaseResponse> updateUserProfile(@Valid @RequestBody UserProfileUpdateDto userProfileUpdateDto, Principal principal) {
    String userEmail = principal.getName();
    User updateAction = userService.updateProfile(userProfileUpdateDto, userEmail);
    BaseResponse baseResponse = new ResponseWithData<>(updateAction);
    baseResponse.setSuccess(true);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
}
