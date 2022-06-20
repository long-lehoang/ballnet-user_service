package com.ballnet.user.service.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private Long id;
  private String username;
  private String password;
  private Boolean isDelete;

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", isDelete=" + isDelete +
        '}';
  }
}
