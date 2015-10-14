// entry file ?
package assignment2;

import java.util.Scanner;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class Parser {

  PrintStream out;
  Map<IdentifierInterface, SetInterface<NaturalNumberInterface>> map;

  Parser() {
    map = new Map();
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

    while(in.hasNextLine()) {
      String statement = in.nextLine();
      try {
        processLine(statement);
      } catch (APException e) {
        out.println(e.getMessage());
      }
    }
  }

  void processLine(String statement) throws APException {
    Scanner parser = new Scanner(statement).useDelimiter("");
    //we check whether the input is an assignment, print statement or comment
    // every function we run returns its output so that the print statement always has something to print.
    skipSpaces(parser);


    if(nextCharIsLetter(parser)) { //assignment
      processAssignment(parser);
    } else if(nextCharIs(parser, '?')) { // print job
      parser.next(); // skip the first ? character
      out.println(setToString(processExpression(parser)));
    } else if(nextCharIs(parser, '/')) {
      //a comment. meaning we don't have to do anything with the following input.so
    } else {
      out.println("Invalid input detected, please retry");
    }

  }

  void processAssignment(Scanner parser) throws APException {
    IdentifierInterface identifier = readIdentifier(parser);

    //while(!nextCharIs(parser, '=')) { // we got an identifier , the next char should be a '='
      if(!nextCharIs(parser, ' ')) {
        out.println("Incorrect notation : [identifier] = [expression], please retry");
        return;
      } else {
        parser.next();
      }
  //  }

    parser.next(); // we skip past the '='
    skipSpaces(parser);

    SetInterface<NaturalNumberInterface> set = processExpression(parser);
    // eol

    map.addKVPair(identifier, set);
  }


  SetInterface<NaturalNumberInterface> processExpression(Scanner parser) throws APException {
    //expression :
    // term { additive-operator term }
    // so a term, with zero or more additive operators, followed by a term.

    SetInterface<NaturalNumberInterface> term = readTerm(parser);

    while(nextCharIs(parser, ' ')) parser.next();// skip spaces for certainty
    while(nextCharIsAditiveOperator(parser)) {
      while(nextCharIs(parser, ' ')) parser.next();// skip spaces

      String operator = parser.next();

      SetInterface<NaturalNumberInterface> term2 = readTerm(parser);
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

  SetInterface<NaturalNumberInterface> readTerm(Scanner parser) throws APException {
    SetInterface<NaturalNumberInterface> factor = readFactor(parser);
    skipSpaces(parser);

    while(nextCharIsMultOperator(parser)) {
      String operator = parser.next();// for consistency, this should always be *

      skipSpaces(parser);
      SetInterface<NaturalNumberInterface> factor2 = readFactor(parser);
      skipSpaces(parser);

      if(operator.equals("*")) factor = factor.intersection(factor2); //consistency
    }

    return factor;
  }

  SetInterface<NaturalNumberInterface> readFactor(Scanner parser) throws APException {
    skipSpaces(parser); //redundant but just in case
    SetInterface<NaturalNumberInterface> set;

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
      throw new APException("Incorrect Factor Detected");
    }

    return set;
  }

  SetInterface<NaturalNumberInterface> readSet(Scanner parser) throws APException {
    parser.next(); // the { character
    String number = "";
    SetInterface<NaturalNumberInterface> set = new Set();
    NaturalNumberInterface naturalNumber = null;

    while(!nextCharIs(parser, '}')) {
      skipSpaces(parser);
      if(nextCharIsDigit(parser)) {
        number += parser.next();
      } else if (nextCharIs(parser, ',')) {
        if(number.equals("")) {
          APException e = new APException("Set has a missing number (two comma's)");
          throw e;
        } else {
          naturalNumber.init(number);
          set.addIdentifier(naturalNumber);
        }
      } else {
        APException e = new APException("Character not fit for a set detected, [0-9] and commas");
        throw e;
      }
    }

    parser.next(); // skip the ending }
    skipSpaces(parser); // skip spaces so that the next method will have no problems
    return set;
  }

  SetInterface<NaturalNumberInterface> readComplexFactor(Scanner parser) throws APException {
    // '(' [expression] ')'
    // we read the expression and pass a new scanner with the expression as its string to processExpression
    String expression = "";

    parser.next(); //skip past the '('
    while(!nextCharIs(parser, ')')) {
      if(!parser.hasNext()) {
        throw new APException("Complex factor not ending with a )");
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

    out.println(identifier);

    return new Identifier(identifier);
  }

  String setToString(SetInterface<NaturalNumberInterface> set) {
    SetInterface<NaturalNumberInterface> copy = new Set(set);
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
