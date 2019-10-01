package com.silvertech.training.securevaultassesment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

  public static void main(String[] args) {
    Random random = new Random();
    Vault vault = new Vault(random.nextInt(9999));
    List<Thread> threads = new ArrayList<>();
    threads.add(new AscendingHackerThread(vault));
    threads.add(new DescendingHackerThread(vault));
    threads.add(new PoliceThread());
    for(Thread thread: threads){
      thread.start();
    }
  }
}
