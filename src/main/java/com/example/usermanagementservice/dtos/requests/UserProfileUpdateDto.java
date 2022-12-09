package com.example.usermanagementservice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {

  private String email;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private String password1;
  private String password2;
}
