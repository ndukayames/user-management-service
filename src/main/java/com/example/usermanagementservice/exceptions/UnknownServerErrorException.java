package com.example.usermanagementservice.exceptions;

public class UnknownServerErrorException extends RuntimeException {
public UnknownServerErrorException(String message) {
  super(message);
}
}
