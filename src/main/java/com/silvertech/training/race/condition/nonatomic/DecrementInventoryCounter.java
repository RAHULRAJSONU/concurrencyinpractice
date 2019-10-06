package com.silvertech.training.race.condition.nonatomic;

import java.util.stream.IntStream;

public class DecrementInventoryCounter extends Thread {

  private InventoryCounter inventoryCounter;

  public DecrementInventoryCounter(InventoryCounter inventoryCounter) {
    this.inventoryCounter = inventoryCounter;
  }

  @Override
  public void run() {
    IntStream.range(0, 10000).forEach(i -> this.inventoryCounter.decrementInventory());
  }
}
