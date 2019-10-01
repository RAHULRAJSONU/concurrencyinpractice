package com.silvertech.training.basic;

import java.lang.Thread.UncaughtExceptionHandler;

public class FirstThread implements Runnable{
  public void run(){
    Thread.currentThread().setName("FirstThread");
    Thread.currentThread().setPriority(1);
    System.out.println("We are in new thread-> "+Thread.currentThread().getName());
    throw new RuntimeException("Intentional Exception!");
  }

  public static void main(String[] args) throws InterruptedException {
    final Thread thread = new Thread(new FirstThread());
    System.out.println("We are in thread-> "+Thread.currentThread().getName()+" before starting FirstThread");
    thread.start();
    System.out.println("We are in thread-> "+Thread.currentThread().getName()+" after starting FirstThread");
    Thread.sleep(10000);
    thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      public void uncaughtException(Thread t, Throwable e) {
        System.out.println("A critical error happend: "+thread.getName());
      }
    });
  }
}
