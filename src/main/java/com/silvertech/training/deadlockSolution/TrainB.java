package com.silvertech.training.deadlockSolution;

import java.util.Random;

public class TrainB implements Runnable {

  private TrackIntersectionControle intersection;
  private Random random = new Random();

  public TrainB(TrackIntersectionControle intersection) {
    this.intersection = intersection;
  }

  @Override
  public void run() {
    while (true) {
      long sleepingTime = random.nextInt(5);
      try {
        Thread.sleep(sleepingTime);
      } catch (InterruptedException e) {
      }
      intersection.takeRoadB();
    }
  }
}
