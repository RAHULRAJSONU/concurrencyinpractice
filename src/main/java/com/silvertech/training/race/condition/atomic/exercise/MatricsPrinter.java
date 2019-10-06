package com.silvertech.training.race.condition.atomic.exercise;

public class MatricsPrinter extends Thread {

  private Matrics matrics;

  public MatricsPrinter(Matrics matrics) {
    this.matrics = matrics;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }
      double currentAverage = matrics.getAverage();
      System.out.println("Current Average is: " + currentAverage);
    }
  }
}
