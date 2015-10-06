package assignment1;

import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;

/** IdentifierSet Class for assignment 1 of Advanced Programming
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 *
 **/
public class Identifiers {

  PrintStream out;
  boolean correctSet = false;
  int state = 0;

  Identifiers() {
    out = new PrintStream(System.out);
  }

  void Start() {
    Scanner in = new Scanner(System.in);
    IdentifierSetADT set1, set2;

    while(true) {
      
      do {
        set1 = new IdentifierSet();
        System.out.print("Enter the first set :");
        if(!in.hasNextLine()) System.exit(0);
      } while(!readSet(in, set1));

      do {
        set2 = new IdentifierSet();
        System.out.print("Enter the second set :");
        if(!in.hasNextLine()) System.exit(0);
      } while(!readSet(in, set2));

      printResults(set1, set2);
    }
  }

  boolean readSet(Scanner in, IdentifierSetADT set) {
    String line = in.nextLine();
    Scanner input = new Scanner(line).useDelimiter("");
    String regex = "[a-zA-Z][a-zA-Z0-9]*";

    if(line.length() < 2) {
      out.println("Input too short");
      return false;
    }

    if(line.charAt(0) != '{' || line.charAt(line.length() - 1) != '}') {
      out.println("Set not properly surrounded by { and }");
      return false;
    }

    input.next();// skip '{'

    while(input.hasNext()) {
      if(nextCharIs(input, ' ')) { // skip whitespace
        input.next();
      } else if(nextCharIs(input, '}')) {
        input.next();
        if(input.hasNext()) {
         out.println("Random } detected , or trailing whitespace");
          return false;
        } else {
          return true;
        }
      } else {
        if(!readIdentifier(input, set)) {
          return false;
        }

        if(set.size() > 10) {
          out.println("max size exceeded");
          return false;
        }
      }
    }
    return true;
  }

  boolean readIdentifier(Scanner input, IdentifierSetADT set) {
    String identifier = "";

    while(input.hasNext("[\\S]") && !nextCharIs(input, '}')) {
      if(!nextCharIsAlphaNum(input)) {
        out.println("Incorrect character in Identifier");
        return false;
      } else {
        if(identifier.length() == 0 && nextCharIsDigit(input)) {
          out.println("Identifier is not allowed to start with a digit");
          return false;
        }
        identifier += input.next();
      }
    }

    set.addIdentifier(new Identifier(identifier));
    return true;
  }

  boolean nextCharIs(Scanner in, char c) {
    return in.hasNext(Pattern.quote(c+""));
  }

  boolean nextCharIsAlphaNum (Scanner in) {
    return in.hasNext("[a-zA-Z0-9]");
  }

  boolean nextCharIsDigit (Scanner in) {
    return in.hasNext("[0-9]");
  }

  void printResults(IdentifierSetADT set1, IdentifierSetADT set2) {
    String result;

    result = getSetString(set1.difference(set2));
    out.println("Difference = " + result);

    result = getSetString(set1.intersection(set2));
    out.println("Intersection = " + result);

    try {
      result = getSetString(set1.union(set2));
      out.println("Union = " + result);

      result = getSetString(set1.symmetricDifference(set2));
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
