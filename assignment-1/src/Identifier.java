package assignment1;

/** Identifier Class for assignment 1 of Advanced Programming
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 *
 **/

public class Identifier implements IdentifierADT {

  private String name;

  public void init (String string) {
    name = string;
  }

  public Identifier (String string) {
    name = string;
  }

  public String getName() {
    return name;
  }
    
  public boolean equals(Identifier identifier) {
    boolean equals = name.equals(identifier);
    return equals;
  }
}
