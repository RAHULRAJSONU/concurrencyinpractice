package com.silvertech.training.join.complexCalculation;

import java.math.BigInteger;

public class Application {

  public static void main(String[] args) throws InterruptedException {
    PowerCalculation pow = new PowerCalculation(BigInteger.valueOf(100),BigInteger.valueOf(100));
    pow.setDaemon(true);
    PowerCalculation pow2= new PowerCalculation(BigInteger.valueOf(2000),BigInteger.valueOf(200));
    pow.start();
    pow.join(2000);
    pow2.start();
    pow2.join(2000);
    System.out.println(pow.getResult().add(pow2.getResult()));
  }
}
