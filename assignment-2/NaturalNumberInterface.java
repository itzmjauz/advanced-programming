package assignment2;

/** Interface for the class NaturalNumber
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 * @elements
 *	characters
 * @structure
 *	linear
 * @domain
 *  at least 1 character
 *  only numeric characters
 *  only non-negative, natural numbers
 * @constructor
 *	NaturalNumberInterface(NaturalNumberInterface original);
 *	  <dl>
 *		  <dt><b>POST-condition</b><dd>The new NaturalNumberInterface object is an exaxt copy of original
 *	 </dl>
 * 	NaturalNumberInterface();
 *	  <dl>
 *		  <dt><b>POST-condition</b><dd>The new NaturalNumberInterface object is empty, still needs to be passed a valid value
 *	  </dl>
 *	<br>
 **/

public interface NaturalNumberInterface extends Data<NaturalNumberInterface> {

  /** Initializes the NaturalNumberInterface object with the string as its value
   * @precondition
   *	  the string must be non-empty and contain only digits
   * @postcondition
   *	  The created object contains a natural number as its value as a string
   **/
  public void init (String number);

    /** gets the number contained in the naturalnumber object as String
   * @precondition
   *	  none
   * @postcondition
   *	  returns a valid string;
   **/
  public String number();

  /** Returns whether or not both objects are equal, with an equal natural number
    * @precondition
    *		-
    * @postcondition
    *		true: The natural number is equal to that of the source
    *   false: The natural number is not equal to that of the source
    **/
  public boolean equals(NaturalNumberInterface source);

}
