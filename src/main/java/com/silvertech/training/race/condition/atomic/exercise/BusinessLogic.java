package com.silvertech.training.race.condition.atomic.exercise;

import java.util.Random;

public class BusinessLogic extends Thread {

  private Matrics matrics;
  private Random random = new Random();

  public BusinessLogic(Matrics matrics) {
    this.matrics = matrics;
  }

  @Override
  public void run() {
    while (true) {
      long start = System.currentTimeMillis();
      try {
        Thread.sleep(random.nextInt(10));
      } catch (InterruptedException e) {

      }

      long end = System.currentTimeMillis();
      matrics.addSample(end - start);
    }
  }
}
