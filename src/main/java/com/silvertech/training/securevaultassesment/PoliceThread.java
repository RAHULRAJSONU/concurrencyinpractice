package com.silvertech.training.securevaultassesment;

import java.util.stream.IntStream;

public class PoliceThread extends Thread{

  @Override
  public void run() {
    IntStream.range(0,10).forEach(countDown -> {
      try {
        Thread.sleep(1000);
      }catch (InterruptedException e){}
      System.out.println(countDown);
    });
    System.out.println("Game over for you Hackers");
    System.exit(0);
  }
}
