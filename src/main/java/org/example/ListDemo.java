
package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
  public static void main(String[] args) {
    List<String> aList = new ArrayList<>();
    List<String> bList = new ArrayList<>();
    List<String> linkedlist = new LinkedList<>();

    for (int i = 0; i < 10; i++) {
      aList.add("a" + i);
      bList.add("b" + i);
    }

    List<String> abList = new ArrayList<>();
    abList.addAll(aList);
    abList.addAll(bList);

    abList.remove("a0");
    abList.remove("b6");

    if (abList.contains("a0")) {
      for (String s : abList) {
        System.out.println(s);
      }
    } else {
      System.out.println("oops!");
    }
  }

  private static void innerCycle(String input) {

  }
}
