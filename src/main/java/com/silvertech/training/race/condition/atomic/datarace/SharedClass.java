package com.silvertech.training.race.condition.atomic.datarace;

public class SharedClass {

  // To avoid data race declare both variable as volatile
  private int x = 0;
  private int y = 0;

  public void increment() {
    x++;
    y++;
  }

  public void checkForDataRace() {
    if (x < y) {
      System.out.println("y > x - Data Race is Detected");
    }
  }
}
