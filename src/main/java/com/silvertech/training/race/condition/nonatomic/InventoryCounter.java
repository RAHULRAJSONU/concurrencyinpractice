package com.silvertech.training.race.condition.nonatomic;

public class InventoryCounter {

  private int counter;

  public InventoryCounter(int counter) {
    this.counter = counter;
  }

  public int getCounter() {
    return counter;
  }

  public void incrementInventory() {
    this.counter++;
  }

  public void decrementInventory() {
    this.counter--;
  }
}
