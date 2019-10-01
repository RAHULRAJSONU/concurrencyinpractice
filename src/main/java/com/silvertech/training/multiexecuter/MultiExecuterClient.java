package com.silvertech.training.multiexecuter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiExecuterClient {
  private MultiExecuter multiExecuter;

  public MultiExecuterClient(MultiExecuter multiExecuter) {
    this.multiExecuter = multiExecuter;
  }

  public static void main(String[] args) {
    List<Runnable> tasks = new ArrayList<>();
    IntStream.range(0,10).forEach(iteration-> {
      tasks.add(new Task());
    });
    MultiExecuterClient mec = new MultiExecuterClient(new MultiExecuter(tasks));
    mec.multiExecuter.executeAll();
  }

  private static class Task implements Runnable{
    @Override
    public void run() {
      System.out.println("Running task: ##");
    }
  }
}
