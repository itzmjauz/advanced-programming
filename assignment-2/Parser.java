// entry file ?
package assignment2;

import java.util.Scanner;
import java.io.PrintStream;


public class Parser {

  PrintStream out;
  Map<IdenitfierInterface, SetInterface<NaturalNumberInterface>> map;

  Parser() {
    map = new Map<>();
    out = new PrintStream(System.out);
  }

  void Start() {
    Scanner in = new Scanner(System.in);

    do {
      System.out.println("$ :");
      if(!in.hasNextLine()) System.exit(0);
      parse(in.nextLine());
    } while(true);
  }

  void parse(String nextLine) {
    // the input should be split in relevant elements/pieces
    Scanner in = new Scanner(nextLine).useDelimiter("");

    while(in.hasNext()) {
      // the structure I would follow would be that Any function would accept a full command as its input
      // and checks if it can process it, if it cant it will try to hand it to other functions.
      // for example ? (A + B)  would pass (A+B) to the ? function

      // the ? functino detects that A+B should be calculated and hands A,B to the + function.

      // parsing this would be easiest by putting it into some sort of stack, from which we get a tree
      // of operations based on the priority of the operators .
      // for assignments we simply have a left and rightside,
      // the left side is the key, the right side the data. the right side can be calculated before
      // put into the data storage, but this isn't necessary.

      // The add funtion A + B, has to match the types A and B to something. naturalnumbers hopefully.
      // we should throw exceptions there. since one should be allowed to assign strings to variables.

      String statement = in.nextLine();
      processLine(statement);
    }
  }

  Set processLine(String statement) {
    Scanner parser = new Scanner(statement).useDelimiter("");
    //we check whether the input is an assignment, print statement or comment
    // every function we run returns its output so that the print statement always has something to print.
    while(parser.hasNext()) {
      out.println(parser.next());
    }
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

  public static void main(String[] args) {
    new Identifiers().Start();
  }
}
