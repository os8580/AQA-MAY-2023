package org.example;

public class Truck extends Car {

  public void attachContainer() {
    System.out.println("Attached container");
  }

  @Override
  public void driveToCity(String destination) {
    System.out.println("A " + color + " truck is delivering cargo to: " + destination);
  }
}
