package assignment2;

public class Identifier extends Data implements IdentifierInterface {

  private String identifier = "";

  public void init(String string) {
    identifier = string;
  }

  public String toString(){
    return identifier;
  }

  public boolean equals(IdentifierInterface source) {
    return identifier.equals(source.toString());
  }
}
