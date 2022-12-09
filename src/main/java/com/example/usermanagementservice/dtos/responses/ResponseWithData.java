package com.example.usermanagementservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWithData<T> extends BaseResponse{
  private T result;

public ResponseWithData(boolean success, T result) {
  super(success);
  this.result = result;
}
}