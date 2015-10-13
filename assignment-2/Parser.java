// entry file ?
package assignment2;

import java.util.Scanner;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class Parser {

  PrintStream out;
  Map<IdentifierInterface, SetInterface<NaturalNumberInterface>> map;

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
      String statement = in.nextLine();
      processLine(statement);
    }
  }

  void processLine(String statement) {
    Scanner parser = new Scanner(statement).useDelimiter("");
    //we check whether the input is an assignment, print statement or comment
    // every function we run returns its output so that the print statement always has something to print.
    skipSpaces(parser);

    while(parser.hasNext()) {
      if(nextCharIsLetter(parser)) { //assignment
        processAssignment(parser);
      } else if(nextCharIs(parser, '?')) { // print job
        parser.next(); // skip the first ? character
        out.println(setToString(processExpression(parser)));
      } else if(nextCharIs(parser, '/')) {
        //a comment. meaning we don't have to do anything with the following input.so
        break;
      } else {
        out.println("Invalid input detected, please retry");
        break;
      }
    }
  }

  void processAssignment(Scanner parser) {
    IdentifierInterface identifier = readIdentifier(parser);

    while(!nextCharIs(parser, '=')) { // we got an identifier , the next char should be a '='
      if(!nextCharIs(parser, ' ')) {
        out.println("Incorrect notation : [identifier] = [expression], please retry");
        return;
      } else {
        parser.next();
      }
    }

    parser.next(); // we skip past the '='
    skipSpaces(parser);

    SetInterface set = processExpression(parser);

    map.addKVPair(identifier, set);
  }


  SetInterface processExpression(Scanner parser) {
    //expression :
    // term { additive-operator term }
    // so a term, with zero or more additive operators, followed by a term.

    SetInterface term = readTerm(parser);

    while(nextCharIs(parser, ' ')) parser.next();// skip spaces for certainty
    while(nextCharIsAditiveOperator(parser)) {
      while(nextCharIs(parser, ' ')) parser.next();// skip spaces

      String operator = parser.next();

      SetInterface term2 = readTerm(parser);
      if(operator.equals("+")) {
        term = term.union(term2);
      } else if(operator.equals("-")) {
        term = term.difference(term2);
      } else if(operator.equals("|")) {
        term = term.symmetricDifference(term2);
      }
    }

    return term;
  }

  SetInterface readTerm(Scanner parser) {
    SetInterface factor = readFactor(parser);
    skipSpaces(parser);

    while(nextCharIsMultOperator(parser)) {
      String operator = parser.next();// for consistency, this should always be *

      skipSpaces(parser);
      SetInterface factor2 = readFactor(parser);
      skipSpaces(parser);

      if(operator.equals("*")) factor = factor.intersection(factor2); //consistency
    }

    return factor;
  }

  SetInterface readFactor(Scanner parser) throws APException {
    skipSpaces(parser); //redundant but just in case
    SetInterface set;

    if(nextCharIsLetter(parser)) {
      IdentifierInterface identifier = readIdentifier(parser);
      skipSpaces(parser);
      //TODO retrieve identifier from key storage
      set = map.returnValue(identifier);
    } else if (nextCharIs(parser, '{')) {
      set = readSet(parser);
    } else if (nextCharIs(parser, '(')) {
      set = readComplexFactor(parser);
    } else {
      Exception e = new APException("Incorrect Factor Detected");
      throw e;
    }

    return set;
  }

  SetInterface readSet(Scanner parser) throws APException {
    parser.next(); // the { character
    String number = "";
    SetInterface set = new Set();
    NaturalNumberInterface naturalNumber;

    while(!nextCharIs(parser, '}')) {
      if(nextCharIsDigit(parser)) {
        number += parser.next();
      } else if (nextCharIs(parser, ',')) {
        if(number.equals("")) {
          Exception e = new APException("Set has a missing number (two comma's)");
          throw e;
        } else {
          naturalNumber.init(number);
          set.addIdentifier(naturalNumber);
        }
      } else {
        Exception e = new APException("Character not fit for a set detected, [0-9] and commas");
        throw e;
      }
    }

    parser.next(); // skip the ending }
    skipSpaces(parser); // skip spaces so that the next method will have no problems
    return set;
  }

  SetInterface readComplexFactor(Scanner parser) throws APException {
    // '(' [expression] ')'
    // we read the expression and pass a new scanner with the expression as its string to processExpression
    String expression = "";

    parser.next(); //skip past the '('
    while(!nextCharIs(parser, ')')) {
      if(!parser.hasNext()) {
        Exception e = new APException("Complex factor not ending with a )");
        throw e;
      }
      expression += parser.next();
    }

    Scanner subParser = new Scanner(expression).useDelimiter("");
    return processExpression(subParser);
  }

  IdentifierInterface readIdentifier(Scanner parser) {
    String identifier = parser.next();

    while(nextCharIsAlphaNum(parser)) {
      identifier += parser.next();
    }

    return new Identifier(identifier);
  }

  String setToString(SetInterface set) {
    SetInterface copy = new Set(set);
    String string = "{ ";

    for(int i = copy.size() ; i > 0 ; i--) {
      string = string + copy.getIdentifier().toString() + " ";
      copy.removeIdentifier();
    }
    return string + "}";
  }

  void skipSpaces(Scanner in) {
    while(nextCharIs(in, ' ')) in.next();
  }

  boolean nextCharIs(Scanner in, char c) {
    return in.hasNext(Pattern.quote(c+""));
  }

  boolean nextCharIsAlphaNum(Scanner in) {
    return in.hasNext("[a-zA-Z0-9]");
  }

  boolean nextCharIsDigit(Scanner in) {
    return in.hasNext("[0-9]");
  }

  boolean nextCharIsLetter(Scanner in) {
    return in.hasNext("[a-zA-Z]");
  }

  boolean nextCharIsAditiveOperator(Scanner in) {
    return in.hasNext("[\\+\\-\\|]");
  }

  boolean nextCharIsMultOperator(Scanner in) {
    return in.hasNext("[\\*]");
  }

  public static void main(String[] args) {
    new Parser().Start();
  }
}
