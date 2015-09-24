package assignment1;

import java.util.Scanner;
import java.io.*;

public class Identifiers {
  //Name              : Antoni Stevenet
  //                  : Tim Nederveen
  //Assignment        : 1 ~ Advanced Programming
  //Date              : 2015-Sep09-Fri11

  PrintStream out;
  int state = 0;

  Identifiers() {
    out = new PrintStream(System.out);
  }

  void Start() {
    Scanner in = new Scanner(System.in);
    IdentifierSetADT set2 = new IdentifierSet();
    IdentifierSetADT set1 = new IdentifierSet();

    out.print("Enter first set : ");
    while(in.hasNextLine()) {
      if(state == 0) {
        set1 = readSet(in.nextLine());
        if(state == 0) {
          out.print("Enter first set : ");
        } else if (state == 1) {
          out.print("Enter second set : ");
        }
      } else if (state == 1) {
        set2 = readSet(in.nextLine());
        if(state == 1) {
          out.print("Enter second set : ");
        }
      } else if (state == 2) {
        printResults(set1, set2);
        set1 = new IdentifierSet();
        set2 = new IdentifierSet();
        out.print("Enter first set : ");
      }
    }

    in.close();
    out.println("Program terminated");
  }

  IdentifierSetADT readSet(String nextLine) {
    IdentifierSetADT set = new IdentifierSet();
    Scanner in = new Scanner(nextLine).useDelimiter("[\\s]");
    String identifierRegex = "[a-zA-Z]([a-zA-Z0-9]+)?";
    System.out.println(in.nextLine());
    while(in.hasNext()) {
      if(in.hasNext(identifierRegex)) {
        IdentifierADT identifier = new Identifier(in.next(identifierRegex));
        set.addIdentifier(identifier);
      } else {
        String output = in.next();
        if(output.length() > 0) {
          out.println("Incorrect element : " + output);
          return new IdentifierSet();
        }
      }
    }

    if(nextLine.indexOf('{') >= 0 && nextLine.indexOf('}') >= 0) {
      return set;
    } else {
      out.println("Error, list wasn't surrounded by {}, please retry");
      state = 0;
      return new IdentifierSet();
    }
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
    IdentifierSetADT copy = new IdentifierSet(set);
    String string = "{ ";

    for(int i = copy.size() ; i > 0 ; i--) {
      string = string + copy.getIdentifier().toString() + " ";
      copy.removeIdentifier();
    }
    return string + "}";
  }

  public static void main(String[] args) {
    new Identifiers().Start();
  }
}
