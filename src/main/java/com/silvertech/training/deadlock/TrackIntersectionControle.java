package com.silvertech.training.deadlock;

public class TrackIntersectionControle {

  private Object roadA = new Object();
  private Object roadB = new Object();

  public void takeRoadA() {
    synchronized (roadA) {
      System.out.println("Road A is locked by thread: " + Thread.currentThread().getName());
      synchronized (roadB) {
        System.out.println("Train is passing through road A");
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
        }
      }
    }
  }

  public void takeRoadB() {
    synchronized (roadB) {
      System.out.println("Road B is locked by thread: " + Thread.currentThread().getName());
      synchronized (roadA) {
        System.out.println("Train is passing through road B");
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
        }
      }
    }
  }
}
