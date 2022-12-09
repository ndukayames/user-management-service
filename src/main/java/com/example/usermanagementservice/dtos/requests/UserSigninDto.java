package com.example.usermanagementservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSigninDto {
  @NotBlank(message = "please enter email")
  private String email;
  @NotBlank(message = "please enter password")
  private String password;
}