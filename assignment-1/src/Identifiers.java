package assignment1;

import java.util.Scanner;
import java.io.PrintStream;
import java.util.regex.Pattern;

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
    Scanner in = new Scanner(System.in).useDelimiter("");
    out.println("Give the first set: ");
    IdentifierSetADT set1 = readSet(in);
    out.println("Give the second set: ");
    IdentifierSetADT set2 = readSet(in);

    in.close();

    printResults(set1, set2);
  }

  IdentifierSetADT readSet(Scanner in) {
    IdentifierSetADT set = new IdentifierSet();

    while(in.hasNext()) {
      if(nextStringIsIdentifier(in)) {
        IdentifierADT identifier = new Identifier(in.next());
        set.AddIdentifier(identifier);
      }
    }

    return set;
  }

  boolean nextStringIsIdentifier(Scanner in) {
    return in.hasNext("^[a-zA-Z][a-zA-Z0-9]+");
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
    String string = "{";

    for(int i = 0 ; i < set.size() ; i++) {
      string = string + set.getIdentifier().getName() + " ";
    }

    return string + "}";
  }
}
