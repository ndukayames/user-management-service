package com.example.usermanagementservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithData<T> extends BaseResponse{
  private T result;

public ResponseWithData(boolean success, T result) {
  super(success);
  this.result = result;
}
}