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
    IdentifierSetADT set1, set2;

    out.print("Give the first set : ");
    while(in.hasNextLine()) {
      set1 = readSet(in.nextLine());

      out.print("Give the second set :");
      set2 = readSet(in.nextLine());

      printResults(set1, set2);
      out.print("Give the first set : ");
    }

    in.close();
    out.println("Program terminated");
  }

  IdentifierSetADT readSet(String nextLine) {
    IdentifierSetADT set = new IdentifierSet();
    Scanner in = new Scanner(nextLine).useDelimiter("[\\{\\}\\s]");
    String identifierRegex = "[a-zA-Z]([a-zA-Z0-9]+)?";

    while(in.hasNext()) {
      if(in.hasNext(identifierRegex)) {
        IdentifierADT identifier = new Identifier(in.next(identifierRegex));
        set.addIdentifier(identifier);
      } else {
        in.next();
      }
    }
    return set;
  }

  void printResults(IdentifierSetADT set1, IdentifierSetADT set2) {
    String result;
    // print string method not yet implemented but
    // assuming IdentifierSet.toString() prints the set in string format
    // handle exceptions later
    result = getSetString(set1.Difference(set2));
    out.println("Difference = " + result);
    result = getSetString(set1.Intersection(set2));
    out.println("Intersection = " + result);

    try {
      result = getSetString(set1.Union(set2));
      out.println("Union = " + result);
      result = getSetString(set1.SymmetricDifference(set2));
      out.println("Symmetric Difference = " + result);
    }
    catch (Exception e){
      out.println(e);
    }
  }

  String getSetString(IdentifierSetADT set) {
    String string = "{ ";

    for(int i = 0 ; i < set.size() ; i++) {
      string = string + set.getIdentifier().toString() + " ";
      set.removeIdentifier();
    }
    return string + "}";
  }

  public static void main(String[] args) {
    new Identifiers().Start();
  }
}
