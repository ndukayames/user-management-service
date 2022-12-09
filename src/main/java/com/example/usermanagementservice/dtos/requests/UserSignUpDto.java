package com.example.usermanagementservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSignUpDto {
  @NotBlank(message = "First name cannot be empty!")
  private String firstName;
  @NotBlank(message = "last name cannot be empty!")
  private String lastName;
  @NotBlank(message = "email cannot be empty!")
  private String email;
  @NotBlank(message = "phone number cannot be empty!")
  private String phoneNumber;
  @NotBlank(message = "Please enter password!")
  private String password1;
  @NotBlank(message = "Enter password again to be double sure!")
  private String password2;
}
