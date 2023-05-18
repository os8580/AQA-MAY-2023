package org.example;

/*
 XXXXXXXXXXXXXXXXXXXXXXXabcXXXXXXXX
 XXXXXXXXXabcXXXXXXXXXXXXXXXXXXXXXX
 XXXXXXXXXXXXXXgreenOOOOOOOOOOOOXXX
 XXXXXXXXXXXX000000XXXXXXXXXXXXXXXX
 XXXXXblueOOOOOOOOOOOOOXXXXXXXXXXXX
 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 XXXX50XXXXXXXXXXXXXXXX100XXXXXXXXX
 */

public class Main {
  public static void main(String[] args) {
    Car redCar = new Car();
    int maxSpeed = 50;
//
//    CarService carService = new CarService();
//    carService.paintCar(redCar, "green");
//    carService.smth(maxSpeed);
//    System.out.println(maxSpeed);
//
//    redCar.driveToCity("Tallin");

    String s1 = "abc";
    String s2 = "abc";

    System.out.println(s1.equals(s2));
    System.out.println(1 == 1);
    System.out.println(s1 == s2);

    Object o = new Object();

//    Car blueCar = new Car();
//    blueCar.color = "blue";
//    redCar.driveToCity("Warsaw", "80");
//    blueCar.driveToCity("Berlin");
//
//    Truck truck = new Truck();
//    truck.color = "yellow";
//    truck.attachContainer();
//    truck.driveToCity("Stockholm");
//
//    Motorbike motorbike = new Motorbike();
//
    driveLeftRight(redCar);
//    driveLeftRight(blueCar);
//    driveLeftRight(truck);
//    driveLeftRight(motorbike);
  }

  public static void driveLeftRight(DrivingPanel drivingPanel) {
    drivingPanel.turnLeft();
    drivingPanel.turnRight();
  }
}