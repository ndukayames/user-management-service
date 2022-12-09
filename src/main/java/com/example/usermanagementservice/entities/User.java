package com.example.usermanagementservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "first_name", nullable = false, length = 255)
  private String firstName;
  @Column(name = "last_name", nullable = false, length = 255)
  private String lastName;
  @Column(name = "email", nullable = false, length = 255)
  private String email;
  @Column(name = "phone_number", nullable = false, length = 255)
  private String phoneNumber;
  @Column(name = "password", nullable = false, length = 255)
  private String password;


}
