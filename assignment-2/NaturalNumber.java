package assignment2;

public class NaturalNumber implements NaturalNumberInterface {

  private String number;

  NaturalNumber (String number) {
    this.number = number;
  }

  NaturalNumber (NaturalNumber source) {
    this.number = source.number();
  }

  public void init(String number) {
    this.number = number;
  }

  public String number() {
    return number;
  }

  public boolean equals(NaturalNumber source) {
    if(number.equals(source.number())) {
      return true;
    } else {
      return false;
    }
  }

  public NaturalNumber clone() {
    return new NaturalNumber(number);
  }

  public int compareTo(Object source) {
    NaturalNumber source2 = (NaturalNumber) source;
    return number.compareTo(source2.number());
  }
}
