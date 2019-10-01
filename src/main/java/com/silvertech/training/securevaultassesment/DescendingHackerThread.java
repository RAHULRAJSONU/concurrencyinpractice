package com.silvertech.training.securevaultassesment;

import java.util.stream.IntStream;

public class DescendingHackerThread extends HackerThread{

  private static final int MAX_PASSWORD = 9999;

  public DescendingHackerThread(Vault vault) {
    super(vault);
  }

  @Override
  public void run() {
    IntStream.range(MAX_PASSWORD,0).forEach(gues -> {
      if(vault.isCorrectPassword(gues)){
        System.out.println(this.getName()+" guessed the password "+gues);
        System.exit(0);
      }
    });
  }
}
