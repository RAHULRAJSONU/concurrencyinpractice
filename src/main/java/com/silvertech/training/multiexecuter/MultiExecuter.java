package com.silvertech.training.multiexecuter;

import java.util.List;

public class MultiExecuter {

  private List<Runnable> tasks;

  public MultiExecuter(List<Runnable> tasks){
    this.tasks = tasks;
  }

  public void executeAll(){
    tasks.forEach(task ->{
      Thread thread = new Thread(task);
      thread.setName(task.getClass().getSimpleName());
      System.out.println("Starting the thread: "+thread.getName());
      thread.start();
    });
  }
}
