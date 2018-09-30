import java.io.*;

import java.util.Scanner;
public class MainClass {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Type in : ");
    String name = scanner.next();
    System.out.println("Your input is : " + name);
  }
}