package com.silvertech.training.securevaultassesment;

import java.util.stream.IntStream;

public class AscendingHackerThread extends HackerThread{
  private static final int MAX_PASSWORD = 9999;

  public AscendingHackerThread(Vault vault) {
    super(vault);
  }

  @Override
  public void run() {
    IntStream.range(0, MAX_PASSWORD).forEach(guess -> {
      if(vault.isCorrectPassword(guess)){
        System.out.println(this.getName()+" guessed the password "+guess);
        System.exit(0);
      }
    });
  }
}
