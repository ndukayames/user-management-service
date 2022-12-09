package com.example.usermanagementservice.exceptions;

public class IncorrectPasswordException extends RuntimeException {
public IncorrectPasswordException(String message) {
  super(message);
}
}
