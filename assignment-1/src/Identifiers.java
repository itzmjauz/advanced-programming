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
    IdentifierSetADT set1 = new IdentifierSet();
    IdentifierSetADT set2 = new IdentifierSet();

    out.print("Enter the first set : ");
    while(in.hasNextLine()) {
      if(state == 0) {
        set1 = readSet(in);
        if(state == 1) {
          out.print("Enter the second set : ");
        } else {
          out.print("Enter the first set : ");
        }
      } else if (state == 1) {
        set2 = readSet(in);
        if(state == 1) {
          out.print("Enter the second set : ");
        }
      }

      if (state == 2) {
        printResults(set1, set2);
        set1 = new IdentifierSet();
        set2 = new IdentifierSet();
        state = 0;
        out.print("Enther the first set : ");
      }
    }

    in.close();
    out.println("Program terminated");
  }

  IdentifierSetADT readSet(Scanner in) {
    IdentifierSetADT set = new IdentifierSet();
    String line = in.nextLine();
    Scanner input = new Scanner(line).useDelimiter("");
    if(line.equals("")) {
      out.println("Input was empty");
      return set;
    }
    input.next(); // skip the { at the start
    String identifier = "";
    Identifier entry;
    correctSet = true;

    if(line.charAt(0) != '{') {
      out.println("Wrong start of set, use { , please retry : ");
      return set;
    } else if(line.charAt(line.length() - 1) != '}') {
      out.println("Set not closed properly, use } , please retry : ");
      return set;
    } else {
      while(input.hasNext()) {
        if(nextCharIs(input, ' ')) {
          input.next();
          if(identifier.length() > 0) {
            entry = new Identifier(identifier);
            set.addIdentifier(entry);
            identifier = "";
            if(set.size() > 10) {
              out.println("Set exceeded the max size of ten!");
              return set;
            }
          }
        } else if (nextCharIsAlphaNum(input)) {
          if(identifier.length() == 0 && nextCharIsDigit(input)) {
            out.println("Identifier didn't start with a letter. please retry : ");
            return set;
          } else {
            identifier = identifier + input.next();
          }
        } else if (nextCharIs(input, '{')) {
          out.println("Random { detected, please retry : ");
          return set;
        } else if (nextCharIs(input, '}')) {
          input.next();
          if(input.hasNext()) {
            out.println("} detected , but wasn't at end of line, please retry : ");
            return set;
          } else {
            if(identifier.length() > 0) {
              entry = new Identifier(identifier);
              set.addIdentifier(entry);
              identifier = "";
              if(set.size() > 10) {
                out.println("Set exceeded the max size of ten!");
                return set;
              }
            }
            state++;
            return set;
          }
        } else {
          out.println("There was an unrecognized (not allowed) character in the string, please retry : ");
          return set;
        }
      }
    }
    state++;
    return set;
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
