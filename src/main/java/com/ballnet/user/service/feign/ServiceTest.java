package com.ballnet.user.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-test")
public interface ServiceTest {
  @RequestMapping("/test/{id}")
  void test(@PathVariable("id") long id);
}
