package com.silvertech.training.reenterantLockAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProductReviewsService {

  private final HashMap<Integer, List<String>> productIdToReviews;
  private ReentrantReadWriteLock reentrantReadWriteLock;

  public ProductReviewsService() {
    this.productIdToReviews = new HashMap<>();
    this.reentrantReadWriteLock = new ReentrantReadWriteLock();
  }

  public void addProduct(int productId) {
    Lock lock = getLockForAddProduct();
    lock.lock();
    try {
      if (!productIdToReviews.containsKey(productId)) {
        productIdToReviews.put(productId, new ArrayList<>());
      }
    } finally {
      lock.unlock();
    }
  }

  public void removeProduct(int productId) {
    Lock lock = getLockForRemoveProduct();
    lock.lock();
    try {
      productIdToReviews.remove(productId);
    } finally {
      lock.unlock();
    }
  }

  public void addProductReview(int productId, String review) {
    Lock lock = getLockForAddProductReview();
    lock.lock();
    try {
      if (!productIdToReviews.containsKey(productId)) {
        addProduct(productId);
      }
    } finally {
      lock.unlock();
    }
    productIdToReviews.get(productId).add(review);
  }

  public List<String> getAllProductReviews(int productId) {
    Lock lock = getLockForGetAllProductReviews();
    lock.lock();
    try {
      if (productIdToReviews.containsKey(productId)) {
        return Collections.unmodifiableList(productIdToReviews.get(productId));
      }
    } finally {
      lock.unlock();
    }
    return Collections.emptyList();
  }

  public Optional<String> getLatestReview(int productId) {
    Lock lock = getLockForGetLatestReview();
    lock.lock();
    try {
      if (productIdToReviews.containsKey(productId) && !productIdToReviews.get(productId)
          .isEmpty()) {
        List<String> reviews = productIdToReviews.get(productId);
        return Optional.of(reviews.get(reviews.size() - 1));
      }
    } finally {
      lock.unlock();
    }
    return Optional.empty();
  }

  public Set<Integer> getAllProductIdsWithReviews() {
    Lock lock = getLockForGetAllProductIdsWithReviews();
    lock.lock();
    try {
      Set<Integer> productsWithReviews = new HashSet<>();
      for (Map.Entry<Integer, List<String>> productEntry : productIdToReviews.entrySet()) {
        if (!productEntry.getValue().isEmpty()) {
          productsWithReviews.add(productEntry.getKey());
        }
      }
      return productsWithReviews;
    } finally {
      lock.unlock();
    }
  }

  private Lock getLockForAddProduct() {
    return this.reentrantReadWriteLock.writeLock();
  }

  private Lock getLockForRemoveProduct() {
    return this.reentrantReadWriteLock.writeLock();
  }

  private Lock getLockForAddProductReview() {
    return this.reentrantReadWriteLock.writeLock();
  }

  private Lock getLockForGetAllProductReviews() {
    return reentrantReadWriteLock.readLock();
  }

  private Lock getLockForGetLatestReview() {
    return reentrantReadWriteLock.readLock();
  }

  private Lock getLockForGetAllProductIdsWithReviews() {
    return reentrantReadWriteLock.readLock();
  }

}
