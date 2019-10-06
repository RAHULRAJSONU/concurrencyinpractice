package com.silvertech.training.readWriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcurrencySimulator {

  private static final int HIGHEST_PRICE = 1000;
  private InventoryDatabase inventoryDatabase = new InventoryDatabase();

  public void simulate() {
    Random random = new Random();
    for (int i = 0; i < 100000; i++) {
      inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
    }

    Thread writer = new Thread(() -> {
      while (true) {
        inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
        inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {

        }
      }
    });

    writer.setDaemon(true);
    writer.start();

    int numberOfReaderThreads = 7;
    List<Thread> readers = new ArrayList<>();
    for (int readerIndex = 0; readerIndex < numberOfReaderThreads; readerIndex++) {
      Thread reader = new Thread(() -> {
        for (int i = 0; i < 100000; i++) {
          int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
          int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
          inventoryDatabase.getNumberOfItemInPriceRange(lowerBoundPrice, upperBoundPrice);
        }
      });
      reader.setDaemon(true);
      readers.add(reader);
    }
    long startReadingTime = System.currentTimeMillis();
    for (Thread reader : readers) {
      reader.start();
    }

    for (Thread reader : readers) {
      try {
        reader.join();
      } catch (InterruptedException e) {
      }
    }
    long endReadingTime = System.currentTimeMillis();
    System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
  }
}
