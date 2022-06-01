package com.ballnet.user.service.exception;

import com.ballnet.user.service.utils.AppCode;

public class InvalidTokenException extends AppException {

  public InvalidTokenException() {
    super(AppCode.TOKEN_INVALID, "");
  }
}
