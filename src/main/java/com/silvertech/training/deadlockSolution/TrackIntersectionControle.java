package com.silvertech.training.deadlockSolution;

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
    synchronized (roadA) {
      System.out.println("Road A is locked by thread: " + Thread.currentThread().getName());
      synchronized (roadB) {
        System.out.println("Train is passing through road B");
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
        }
      }
    }
  }
}
