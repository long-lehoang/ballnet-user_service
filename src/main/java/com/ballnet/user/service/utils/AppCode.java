package com.ballnet.user.service.utils;

public enum AppCode {
  SUCCESS("code.success"),
  USER_ERROR("code.user.error"),
  USER_DUPLICATED("code.user.duplicated"),
  USER_UNAUTHORIZED("code.user.unauthorized"),
  TOKEN_INVALID("code.token.invalid");
  final String message;

  AppCode(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
