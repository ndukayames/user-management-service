package com.example.usermanagementservice.exceptions;

public class UserDoesNotExistException extends RuntimeException {
public UserDoesNotExistException(String message) {
  super(message);
}
}
