package com.silvertech.training.race.condition.nonatomic;

public class Application {

  public static void main(String[] args) throws InterruptedException {
    InventoryCounter inventoryCounter = new InventoryCounter(0);
    IncrementInventoryThread incrementInventoryThread = new IncrementInventoryThread(
        inventoryCounter);
    DecrementInventoryCounter decrementInventoryCounter = new DecrementInventoryCounter(
        inventoryCounter);
    incrementInventoryThread.start();
    decrementInventoryCounter.start();
    incrementInventoryThread.join();
    decrementInventoryCounter.join();
    System.out.println(inventoryCounter.getCounter());
  }
}
