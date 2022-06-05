package com.ballnet.user.service.api;

import com.ballnet.user.service.api.request.LoginRequest;
import com.ballnet.user.service.api.response.AuthResponse;
import com.ballnet.user.service.api.response.LoginResponse;
import com.ballnet.user.service.exception.InvalidTokenException;
import com.ballnet.user.service.exception.UserUnauthorizedException;
import com.ballnet.user.service.model.Credential;
import com.ballnet.user.service.service.AuthService;
import com.ballnet.user.service.service.TokenService;
import com.ballnet.user.service.utils.AppResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

  private final AuthService authService;
  private final TokenService tokenService;


  @PostMapping("/login")
  public AppResponse<LoginResponse> login(@RequestBody @Validated LoginRequest request) {

    var user = authService.authentication(Credential.builder()
        .username(request.getUsername())
        .password(request.getPassword())
        .build());

    if (user.isEmpty()) {
      throw new UserUnauthorizedException();
    }

    return AppResponse.ok(new LoginResponse(tokenService.generateToken(user.get())));
  }

  @PostMapping()
  public AppResponse<AuthResponse> auth(@RequestHeader("Authorization") String authHeader){
    var roles = authService.validateToken(authHeader);

    if(roles.isEmpty()){
      throw new InvalidTokenException();
    }

    return AppResponse.ok(new AuthResponse(roles.get()));
  }
}
