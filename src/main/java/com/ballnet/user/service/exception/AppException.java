package com.ballnet.user.service.exception;

import com.ballnet.user.service.utils.AppCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
  private final AppCode code;
  private String message;

  public AppException(AppCode code, Throwable throwable) {
    super(throwable);
    this.code = code;
  }
}
