package com.silvertech.training.deadlockSolution;

public class Application {

  public static void main(String[] args) {
    TrackIntersectionControle intersection = new TrackIntersectionControle();
    Thread trainA = new Thread(new TrainA(intersection));
    Thread trainB = new Thread(new TrainB(intersection));
    trainA.start();
    trainB.start();
  }
}
