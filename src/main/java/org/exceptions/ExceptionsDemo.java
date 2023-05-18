package org.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExceptionsDemo {

  public static void main(String[] args) {
    try {
//      readFile();
      stringLength("aaa");
      stringLength("");
      stringLength(null);
    } catch (Throwable t) {
    } finally {
      System.out.println("This will be executed always");
    }
  }

  public static void readFile() throws FileNotFoundException {
    File file = new File("a.xml");
    FileReader fileReader = new FileReader(file);
  }

  public static void stringLength(String s) {
    System.out.println(s.length());
  }
}
