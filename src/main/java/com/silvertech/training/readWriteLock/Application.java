package com.silvertech.training.readWriteLock;

public class Application {

  public static void main(String[] args) {
    ConcurrencySimulator cs = new ConcurrencySimulator();
    cs.simulate();
  }
}
