package assignment1;

/** Identifier Class for assignment 1 of Advanced Programming
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 *
 **/

public class Identifier implements IdentifierADT {

  private String name;

  public Identifier (String string) {
    name = string;
  }

  public void init (String string) {
    name = string;
  }

  public String toString() {
    return name;
  }

  public boolean equals(IdentifierADT identifier) {
    return name.equals(identifier.toString());
  }
}
