package assignment2;

public class NaturalNumber implements NaturalNumberInterface {

  private String number;

  NaturalNumber (String number) {
    this.number = number;
  }

  NaturalNumber (NaturalNumberInterface source) {
    this.number = source.number();
  }

  public void init(String number) {
    this.number = number;
  }

  public String number() {
    return number;
  }

  public boolean equals(NaturalNumberInterface source) {
    if(number.equals(source.number())) {
      return true;
    } else {
      return false;
    }
  }

  public NaturalNumberInterface clone() {
    return new NaturalNumber(number);
  }

  public int compareTo(NaturalNumberInterface source) {
    return number.compareTo(source.number());
  }
}
