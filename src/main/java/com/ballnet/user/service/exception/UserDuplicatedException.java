package com.ballnet.user.service.exception;

import com.ballnet.user.service.utils.AppCode;

public class UserDuplicatedException extends AppException {

  public UserDuplicatedException() {
    super(AppCode.USER_DUPLICATED, "");
  }
}
