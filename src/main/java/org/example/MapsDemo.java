package org.example;

import java.util.HashMap;
import java.util.Map;

public class MapsDemo {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();

    map.put(null, "null key");
    map.put("a", "a");
    map.put("null value", null);
    map.put("c", "basdasd");

//    System.out.println(map.get(null));
//    System.out.println(map.get("null value"));

    if (map.containsKey("d")) {
      System.out.println(map.get("d"));
    }
  }
}
