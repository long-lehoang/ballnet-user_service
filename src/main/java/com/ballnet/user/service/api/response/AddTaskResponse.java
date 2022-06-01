package com.ballnet.user.service.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTaskResponse {
  private long id;
  private String title;
  private String description;
}
