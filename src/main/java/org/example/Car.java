package org.example;

public class Car implements DrivingPanel {

  public String color = "red";

  public void driveToCity(String destination) {
    driveToCity(destination, "100");
    useEngine();
  }

  public void driveToCity(String destination, String speed) {
    System.out.println("A " + color + " car is driving to: " + destination + " at speed " + speed);
    useEngine();
  }

  private void useEngine() {
    System.out.println("brrrr");
  }

  @Override
  public void turnLeft() {
    System.out.println("Turning left");
  }

  @Override
  public void turnRight() {
    System.out.println("Turning right");
  }
}
