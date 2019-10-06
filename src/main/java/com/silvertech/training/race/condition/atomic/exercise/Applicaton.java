package com.silvertech.training.race.condition.atomic.exercise;

public class Applicaton {

  public static void main(String[] args) {
    Matrics matrics = new Matrics();
    BusinessLogic businessLogic1 = new BusinessLogic(matrics);
    BusinessLogic businessLogic2 = new BusinessLogic(matrics);
    MatricsPrinter matricsPrinter = new MatricsPrinter(matrics);

    businessLogic1.start();
    businessLogic2.start();
    matricsPrinter.start();
  }
}
