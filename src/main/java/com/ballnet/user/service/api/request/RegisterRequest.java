package com.ballnet.user.service.api.request;

import com.ballnet.user.service.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegisterRequest {
  @NotEmpty(message = "{app.login.request.username.require}")
  private String username;

  @NotEmpty(message = "{app.login.request.password.require}")
  @Pattern(
      regexp = Constants.PASSWORD_SECURITY_PATTERN,
      message = "{app.login.request.password.security.require}")
  private String password;
}
