package org.example;

public class Motorbike implements DrivingPanel {
  @Override
  public void turnLeft() {
    System.out.println("Bike turns Left");
  }

  @Override
  public void turnRight() {
    System.out.println("Bike turns Right");
  }
}
