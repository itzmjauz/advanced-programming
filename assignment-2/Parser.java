// entry file ?
package assignment2;

import java.util.Scanner;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class Parser {

  Scanner in;
  PrintStream out;
  Map<Identifier, Set<NaturalNumber>> map;

  Parser() {
    map = new Map<>();
    out = new PrintStream(System.out);
    in = new Scanner(System.in);
  }

  void Start() {
    in.useDelimiter("");

    while(in.hasNextLine()) {
      try {
        parse();
        in.nextLine();
      } catch (APException e) {
        System.out.println(e);
        in.nextLine();
      }
    }
  }

  private void parse() throws APException { //process statement
    skipSpaces();
    // the input should be split in relevant elements/pieces
    if (nextCharIsLetter()) {
      processAssignment();
    } else if (nextCharIs('?')) {
      processPrintStatement();
    } else if (nextCharIs('/')) {
      //comment, so nothing to do
    } else {
      throw new APException("ERROR : No correct statement given, { assignment | print_statement | comment }");
    }
  }

  private void processAssignment() throws APException {
    Identifier identifier = readIdentifier();

    //while(!nextCharIs(parser, '=')) { // we got an identifier , the next char should be a '='
    skipSpaces();
    if(!nextCharIs('=')) {
      throw new APException("ERROR : Incorrect notation : [identifier] = [expression], please retry");
    }

    in.next(); // skip past the '='
    skipSpaces();
    Set<NaturalNumber> set = processExpression();

    skipSpaces();
    if(!nextCharIs('\n')) {
      throw new APException("ERROR : assignment not ending with eoln");
    } else {
      map.addKVPair(identifier, set);
    }
  }

  private void processPrintStatement() throws APException {
    in.next(); //skip past ?
    skipSpaces();
    Set<NaturalNumber> set = processExpression();
    skipSpaces();
    if(!nextCharIs('\n')) {
      throw new APException("ERROR : print statement not ending correctly");
    }
    System.out.println(setToString(set));
  }

  private Set<NaturalNumber> processExpression() throws APException {
    //expression :
    // term { additive-operator term }
    // so a term, with zero or more additive operators, followed by a term.
    Set<NaturalNumber> term = readTerm();
    skipSpaces(); // jic if complex factor

    while (nextCharIsAditiveOperator()) {
      if (nextCharIs('+')) {
        in.next(); //skip past the +
        term = term.union(readTerm());
      } else if (nextCharIs('-')) {
        in.next(); //skip past the -
        term = term.difference(readTerm());
      } else if (nextCharIs('|')) {
        in.next(); //skip past the |
        term = term.symmetricDifference(readTerm());
      } else {
        throw new APException("ERROR : Invalid expression");
      }
    }

    return term;
  }

  private Set<NaturalNumber> readTerm() throws APException {
    Set<NaturalNumber> factor = readFactor();
    skipSpaces();
    while(nextCharIsMultOperator()) {
      in.next(); // for consistency, this should always be *
      skipSpaces();
      factor = factor.intersection(readFactor()); //consistency
    }

    return factor;
  }

  private Set<NaturalNumber> readFactor() throws APException {
    skipSpaces(); //redundant but just in case
    Set<NaturalNumber> set = new Set<NaturalNumber>();

    if(nextCharIsLetter()) {
      Identifier identifier = readIdentifier();
      if(map.containsKey(identifier)) {
        set = map.returnValue(identifier);
      } else {
        throw new APException("Key/identifier : " + identifier.toString() + " not in storage");
      }
    } else if (nextCharIs('{')) {
      set = readSet();
    } else if (nextCharIs('(')) {
      set = readComplexFactor();
    } else {
      throw new APException("ERROR : Error when attempting to read factor, use identifier, sets or complex factors only");
    }

    skipSpaces(); //just in case
    return set;
  }

  private Set<NaturalNumber> readSet() throws APException {
    in.next(); // the { character
    skipSpaces();
    Set<NaturalNumber> set = new Set<>();

    if (!nextCharIs('}')) {
      do {
        if (nextCharIs(',')) in.next(); //TODO does this work?
        set.add(readNaturalNumber());
        skipSpaces(); //skip spaces after number, before comma  -> , 3[ ],
      } while (nextCharIs(','));
    }

    if (nextCharIs('}')) {
      in.next(); // skip the ending }
      return set;
    } else {
      throw new APException("ERROR : set not probably closed with }");
    }
  }

  private NaturalNumber readNaturalNumber() throws APException {
    String number = "";
    skipSpaces(); //skip preceding spaces

    while (nextCharIsDigit()) {
      number = number + in.nextInt();
    }

    if (number == "") {
      throw new APException("ERROR : non-naturalnumber in set");
    }

    skipSpaces();
    if (nextCharIs(',') || nextCharIs('}') || nextCharIs('\n') || nextCharIs(')')) { //TODO extend with nextCharIs('\n') ??
      return new NaturalNumber(number);
    } else {
      throw new APException("ERROR : Invalid naturalnumber in set");
    }
  }

  private Set<NaturalNumber> readComplexFactor() throws APException {
    // '(' [expression] ')'
    // we read the expression and pass a new scanner with the expression as its string to processExpression

    in.next(); //skip past the '('
    Set<NaturalNumber> complexFactor = processExpression();

    if(nextCharIs(')')) {
      in.next(); //skip past )
    } else {
      throw new APException("Complex factor not ending with a )");
    }

    return complexFactor;
  }

  private Identifier readIdentifier() {
    String identifier = "";

    while(nextCharIsAlphaNum()) {
      identifier += in.next();
    }

    return new Identifier(identifier);
  }

  private String setToString(Set<NaturalNumber> source) {
    String string = "";

    Set<NaturalNumber> clone = source.clone();

    while(!clone.isEmpty()) {
      string += clone.get().number() + " ";
      clone.remove();
    }

    return string;
  }

  private void skipSpaces() {
    in.skip("[ ]*");
  }

  private boolean nextCharIs(char c) {
    return in.hasNext(Pattern.quote(Character.toString(c)));
  }

  private boolean nextCharIsAlphaNum() {
    return in.hasNext("[a-zA-Z0-9]");
  }

  private boolean nextCharIsDigit() {
    return in.hasNext("[0-9]");
  }

  private boolean nextCharIsLetter() {
    return in.hasNext("[a-zA-Z]");
  }

  private boolean nextCharIsAditiveOperator() {
    return in.hasNext("[\\+\\-\\|]");
  }

  private boolean nextCharIsMultOperator() {
    return in.hasNext("[\\*]");
  }

  public static void main(String[] args) {
    new Parser().Start();
  }
}
