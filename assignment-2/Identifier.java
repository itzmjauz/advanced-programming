package assignment2;

public class Identifier implements IdentifierInterface {

  private String identifier = "";

  public Identifier(Identifier source) {
    identifier = source.toString();
  }

  public Identifier(String identifier) {
    this.identifier = identifier;
  }

  public void init(String string) {
    identifier = string;
  }

  public String toString(){
    return identifier;
  }

  public boolean equals(Identifier source) {
    return identifier.equals(source.toString());
  }

  public int compareTo(Object source) {
    Identifier source2 = (Identifier) source;
    return toString().compareTo(source.toString());
  }

  public Identifier clone() {
    return new Identifier(this);
  }
}
