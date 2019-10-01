package com.silvertech.training.join;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class FactorialThread extends Thread{
  private long inputNumber;
  private BigInteger result = BigInteger.ZERO;
  private boolean isFinished = false;

  public FactorialThread(long inputNumber){
    this.inputNumber = inputNumber;
  }

  @Override
  public void run() {
    this.result = factorial(inputNumber);
    this.isFinished = true;
  }

  private BigInteger factorial(long inputNumber) {
    BigInteger temp = BigInteger.ONE;
    for(long i = inputNumber; i>0; i--){
      temp = temp.multiply(new BigInteger(Long.toString(i)));
    }
    return temp;
  }

  public boolean isFinished(){
    return isFinished;
  }

  public BigInteger getResult(){
    return result;
  }
}
