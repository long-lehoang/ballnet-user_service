package com.ballnet.user.service.api;

import com.ballnet.user.service.api.request.RegisterRequest;
import com.ballnet.user.service.api.request.UpdateUserRequest;
import com.ballnet.user.service.api.response.UserResponse;
import com.ballnet.user.service.model.User;
import com.ballnet.user.service.service.UserService;
import com.ballnet.user.service.utils.AppResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

  private final UserService userService;

  @PostMapping()
  public AppResponse<UserResponse> register(@RequestBody @Validated RegisterRequest request) {

    var user = userService.register(
        User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .build());

    return AppResponse.ok(
        UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .build()
    );
  }

  @PutMapping()
  public AppResponse<UserResponse> update(@RequestBody UpdateUserRequest request){
    var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var updateUser = userService.update(User.builder()
        .username(request.getUsername() != null ? request.getUsername() : user.getUsername())
        .password(request.getPassword() != null ? request.getPassword() : user.getPassword())
        .build());

    return AppResponse.ok(
        UserResponse.builder()
            .id(updateUser.getId())
            .username(updateUser.getUsername())
            .build()
    );
  }
}
