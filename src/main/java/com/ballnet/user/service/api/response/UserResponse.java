package com.ballnet.user.service.api.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
  private long id;
  private String username;
}
