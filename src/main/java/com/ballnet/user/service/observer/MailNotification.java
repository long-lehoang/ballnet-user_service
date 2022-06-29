package com.ballnet.user.service.observer;

import com.ballnet.user.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component("mailNotification")
@Slf4j
public class MailNotification implements UserObserver {

  @Autowired
  @Qualifier("registrationKafkaTemplate")
  private KafkaTemplate<String, User> registrationKafkaTemplate;

  @Override
  public void update(Object obj) {

  }

  @Override
  public void add(Object obj) {
    User user = (User)obj;
    ListenableFuture<SendResult<String, User>> future = registrationKafkaTemplate.send("registration", user);

    future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
      @Override
      public void onSuccess(SendResult<String, User> result) {
        log.debug("Sent message=[{}] with offset=[{}]",user.toString(),result.getRecordMetadata().offset());
      }
      @Override
      public void onFailure(Throwable ex) {
        log.debug("Unable to send message=[{}] due to : {}",user.toString(), ex.getMessage());
      }
    });
  }

  @Override
  public void delete(Object obj) {

  }
}
