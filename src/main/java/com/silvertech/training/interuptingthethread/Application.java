package com.silvertech.training.interuptingthethread;

public class Application {

  public static void main(String[] args) {
    Thread t = new Thread(new LongComputationTask());
    t.setDaemon(true);
    t.start();
    t.interrupt();
  }

  private static class LongComputationTask implements Runnable{

    @Override
    public void run() {
      try {
        Thread.sleep(1000000);
      }catch (InterruptedException e){
        System.out.println("Thread Interrupted!!");
      }
    }
  }
}

