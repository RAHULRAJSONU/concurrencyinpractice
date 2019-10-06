package com.silvertech.training.performance.imageprocessing;

import java.io.IOException;

public class Application {

  public static void main(String[] args) {
    ImageRecolourProcessor ircp = new ImageRecolourProcessor();
    try {
      ircp.apply(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

