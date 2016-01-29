package assignment2;

import java.math.BigInteger;

public class NaturalNumber implements NaturalNumberInterface {

  private String number;

  NaturalNumber (String number) {
    this.number = parse(number);
  }

  NaturalNumber (NaturalNumber source) {
    this.number = source.number();
  }

  public void init(String number) {
    this.number = parse(number);
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
    return new BigInteger(source2.number()).compareTo(new BigInteger(number()));
  }

  private String parse(String number) {
    return number.replaceFirst("^0+(?!$)", "");
  }
}
