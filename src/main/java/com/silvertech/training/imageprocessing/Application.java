package com.silvertech.training.imageprocessing;

import java.io.IOException;

public class Application {

  public static void main(String[] args) {
    ImageRecolourProcessor ircp = new ImageRecolourProcessor();
    try {
      ircp.apply();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

