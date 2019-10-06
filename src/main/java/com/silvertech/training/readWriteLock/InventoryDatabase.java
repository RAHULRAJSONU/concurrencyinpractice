package com.silvertech.training.readWriteLock;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InventoryDatabase {

  private TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();
  private ReentrantLock lock = new ReentrantLock();
  private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
  private Lock readLock = reentrantReadWriteLock.readLock();
  private Lock writeLock = reentrantReadWriteLock.writeLock();

  public int getNumberOfItemInPriceRange(int lowerBound, int upperBound) {
    readLock.lock();
    try {
      Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
      Integer toKey = priceToCountMap.floorKey(upperBound);

      if (fromKey == null || toKey == null) {
        return 0;
      }

      NavigableMap<Integer, Integer> rangeOfPrice = priceToCountMap
          .subMap(fromKey, true, toKey, true);
      int sum = 0;
      for (int numberOfItemsForPrice : rangeOfPrice.values()) {
        sum += numberOfItemsForPrice;
      }

      return sum;
    } finally {
      readLock.unlock();
    }
  }

  public void addItem(int price) {
    writeLock.lock();
    try {
      Integer numberOfItemsForPrice = priceToCountMap.get(price);
      if (numberOfItemsForPrice == null) {
        priceToCountMap.put(price, 1);
      } else {
        priceToCountMap.put(price, numberOfItemsForPrice + 1);
      }
    } finally {
      writeLock.unlock();
    }
  }

  public void removeItem(int price) {
    writeLock.lock();
    try {
      Integer numberOfItemsForPrice = priceToCountMap.get(price);
      if (numberOfItemsForPrice == null || numberOfItemsForPrice == 1) {
        priceToCountMap.remove(price);
      } else {
        priceToCountMap.put(price, numberOfItemsForPrice - 1);
      }
    } finally {
      writeLock.unlock();
    }
  }
}
