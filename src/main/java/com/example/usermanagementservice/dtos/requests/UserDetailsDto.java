package com.example.usermanagementservice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
  private String email;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private Long id;
}
