package assignment1;

import java.util.Scanner;
import java.io.PrintStream;

public class Identifiers {
  //Name              : Antoni Stevenet
  //                  : Tim Nederveen
  //Assignment        : 1 ~ Advanced Programming
  //Date              : 2015-Sep09-Fri11

  PrintStream out;

  Identifiers() {
    out = new PrintStream(System.out);
  }

  void Start() {
    Scanner in = new Scanner(System.in);
    out.println(in.nextLine());
  }

  IdentifierSetADT readInput(Scanner scanner) {
    // read identifiers from input and instantly check for correctness
    // and write them to a Set ( which the function returns )
  }
}
