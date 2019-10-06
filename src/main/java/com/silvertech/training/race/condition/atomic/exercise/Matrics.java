package com.silvertech.training.race.condition.atomic.exercise;

public class Matrics {

  private long count = 0;
  private double average = 0.0;

  public synchronized void addSample(long sample) {
    double currentSum = average * count;
    count++;
    average = (currentSum + sample) / count;
  }

  public double getAverage() {
    return average;
  }
}
