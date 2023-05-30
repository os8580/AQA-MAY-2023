package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {

  public static void main(String[] args) {
    Set<String> stringSet = new HashSet<>();
    stringSet.add("a");
    stringSet.add("b");
    stringSet.add("c");
    stringSet.add("d");

    Iterator<String> i = stringSet.iterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }

    stringSet.contains("a");
  }
}
