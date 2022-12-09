package com.example.usermanagementservice.exceptions;

public class UserAlreadyExistException extends RuntimeException {
public UserAlreadyExistException(String message) {
  super(message);
}
}
