package com.ballnet.user.service.observer;

public interface UserObserver {
  public void update(Object obj);
  public void add(Object obj);
  public void delete(Object obj);
}
