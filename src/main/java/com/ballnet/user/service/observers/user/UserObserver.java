package com.ballnet.user.service.observers.user;

public interface UserObserver {
  public void update(Object obj);
  public void add(Object obj);
  public void delete(Object obj);
}
