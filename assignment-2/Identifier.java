package assignment2;

public class Identifier<E extends Data<E>> implements IdentifierInterface<E> {

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

  public boolean equals(IdentifierInterface<E> source) {
    return identifier.equals(source.toString());
  }

  public int compareTo(IdentifierInterface<E> source) {
    return toString().compareTo(source.toString());
  }
}
