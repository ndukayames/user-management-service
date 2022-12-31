package com.example.usermanagementservice.controllers;

import com.example.usermanagementservice.dtos.PageData;
import com.example.usermanagementservice.dtos.requests.UserDetailsDto;
import com.example.usermanagementservice.dtos.requests.UserProfileUpdateDto;
import com.example.usermanagementservice.dtos.requests.UserSignUpDto;
import com.example.usermanagementservice.dtos.requests.UserSigninDto;
import com.example.usermanagementservice.dtos.responses.BaseResponse;
import com.example.usermanagementservice.dtos.responses.ResponseWithData;
import com.example.usermanagementservice.entities.User;
import com.example.usermanagementservice.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
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
  
  @GetMapping("users/me")
  public ResponseEntity<BaseResponse> getUserProfile(Principal principal) {
    String userEmail = principal.getName();
    UserDetailsDto userDetailsDto = userService.getUserProfile(userEmail);
    BaseResponse baseResponse = new ResponseWithData<UserDetailsDto>(userDetailsDto);
    baseResponse.setSuccess(true);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
  
  @GetMapping("users")
public ResponseEntity<BaseResponse> findAllUsers(@RequestParam Map<String,String> allParams) {
    
    // create page data
    PageData pageData = new PageData();
    pageData.setPage(Long.parseLong(allParams.get("page")));
    pageData.setCount(Long.parseLong(allParams.get("count")));
    if(pageData.getPage() == 0 || pageData.getCount() == 0) {
      throw new RuntimeException("Pagination data cannot contain zeroes");
    }
    
    List<UserDetailsDto> findUsersAction = userService.findAllUsers(pageData);
    BaseResponse baseResponse = new ResponseWithData<List<UserDetailsDto>>(findUsersAction);
    baseResponse.setSuccess(false);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }

  @GetMapping("users/{email}")
  public ResponseEntity<BaseResponse> getAnotherUserProfile(@PathVariable("email") String email) {
    UserDetailsDto userDetailsDto = userService.getAnotherUserProfile(email);
    BaseResponse baseResponse = new ResponseWithData<UserDetailsDto>(userDetailsDto);
    baseResponse.setSuccess(true);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
  
  @DeleteMapping("users/me")
  public ResponseEntity<BaseResponse> deleteMyAccount(Principal principal) {
    String userEmail = principal.getName();
    int deleteAction = userService.deleteMyProfile(userEmail);
    if(deleteAction == 1) {
      BaseResponse baseResponse = new ResponseWithData<>(deleteAction);
      baseResponse.setSuccess(true);
      return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    } else {
      throw new RuntimeException("An error occured while trying to delete your profile");
    }
  }
}
