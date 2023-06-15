
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListDemo {
  public static void main(String[] args) {
    List<String> aList = new ArrayList<>();

    aList.add("asdasd");
    aList.add("aoskdjoaisdj");
    aList.add("kaslkmoiiasdoij");

    aList.stream().forEach(
        s -> {
          System.out.println(s);
        }
    );

    List<Integer> bList = aList.stream()
        .filter(s -> s.contains("k"))
        .map(s -> s.length()).collect(Collectors.toList());
    bList.forEach(i -> System.out.println(i));
  }
}
