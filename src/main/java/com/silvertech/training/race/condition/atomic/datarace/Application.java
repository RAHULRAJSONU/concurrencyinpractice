package com.silvertech.training.race.condition.atomic.datarace;

public class Application {

  public static void main(String[] args) {
    SharedClass sharedClass = new SharedClass();
    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < Integer.MAX_VALUE; i++) {
        sharedClass.increment();
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < Integer.MAX_VALUE; i++) {
        sharedClass.checkForDataRace();
      }
    });

    thread1.start();
    thread2.start();
  }
}
