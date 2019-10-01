package com.silvertech.training.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Application {

  public static void main(String[] args) {
    List<Long> inputNumbers = Arrays.asList(100000000L,3435L,35435L,2324L,4656L,23L,5556L);

    List<FactorialThread> threads = new ArrayList<>();
    inputNumbers.forEach(i->threads.add(new FactorialThread(i)));
    threads.forEach(t->{
      t.setDaemon(true);
      t.start();
    });
    threads.forEach(t-> {
      try {
        t.join(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    IntStream.range(0,inputNumbers.size()).forEach(i->{
      FactorialThread factorialThread = threads.get(i);
      if(factorialThread.isFinished()){
        System.out.println("Factorial of "+inputNumbers.get(i)+" is "+factorialThread.getResult());
      }else {
        System.out.println("The calculation for "+inputNumbers.get(i)+" is still in progress");
      }
    });
  }
}
