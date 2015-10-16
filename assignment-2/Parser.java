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
    skipSpaces(parser);
    if(!nextCharIs(parser, '=')) {
      throw new APException("Incorrect notation : [identifier] = [expression], please retry");
    }

    parser.next(); // skip past the '='
    skipSpaces(parser);
    SetInterface<NaturalNumberInterface> set = processExpression(parser);
    out.println(setToString(set));
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
    SetInterface<NaturalNumberInterface> set = new Set<NaturalNumberInterface>();

    if(nextCharIsLetter(parser)) {
      IdentifierInterface identifier = readIdentifier(parser);
      skipSpaces(parser);
      //TODO retrieve identifier from key storage
      if(map.containsKey(identifier)) {
        set = map.returnValue(identifier);
      } else {
        throw new APException("Key/identifier : "+ identifier.toString() +" not in storage");
      }
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
    SetInterface<NaturalNumberInterface> set = new Set<>();

    skipSpaces(parser);
    while(!nextCharIs(parser, '}')) {
      if(!parser.hasNext()) throw new APException("List not properly closed by a }");
      if(nextCharIs(parser, ' ')) parser.next();
      if(nextCharIsDigit(parser)) {
        number = "" + number + parser.next();
      }

      if(nextCharIs(parser, ',')) {
        if(number.equals("")) throw new APException("Comma without preceding number");
        out.println("trying to add : " + number);
        set.add(new NaturalNumber(number));
        number = "";
        parser.next();//skip past the comma
      }
    }

    if(!number.equals("")) {
      out.println("trying to add : " + number);
      set.add(new NaturalNumber(number));
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

    out.println("identfier : " + identifier);

    return new Identifier(identifier);
  }

  String setToString(SetInterface<NaturalNumberInterface> source) {
    if(source == null) System.out.println("source = null");
    SetInterface<NaturalNumberInterface> copy = source.clone();

    String string = "{ ";
    NaturalNumberInterface number;

    while(!copy.isEmpty()) {
      if(!(copy.get() == null)) {
        out.println(copy.get().number());
        //string = string + number.number() + " ";
        copy.remove();
      }
      copy.remove();
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
