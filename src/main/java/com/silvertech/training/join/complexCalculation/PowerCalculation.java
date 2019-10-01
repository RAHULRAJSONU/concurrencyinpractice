package com.silvertech.training.join.complexCalculation;

import java.math.BigInteger;

public class PowerCalculation extends Thread{
  private BigInteger result = BigInteger.ONE;
  private BigInteger base;
  private BigInteger power;

  public PowerCalculation(BigInteger base, BigInteger power){
    this.base = base;
    this.power = power;
  }

  @Override
  public void run() {
    BigInteger count = this.power;
    for(BigInteger i = base; i.compareTo(power) > 0; i.subtract(BigInteger.ONE)){
      result = result.multiply(base);
    }
  }

  public BigInteger getResult(){
    return this.result;
  }
}
