package assignment2;

/** Interface for the class Identifier
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 * @elements
 *	characters
 * @structure
 *	linear
 * @domain
 * at least 1 character
 * always starts with a letter
 * only alphanumeric characters
 * @constructor
 *	IdentifierInterface(IdentifierInterface original);
 *	  <dl>
 *		  <dt><b>POST-condition</b><dd>The new Identifier object is an exaxt copy of original
 *	 </dl>
 * 	IdentifierInterface();
 *	  <dl>
 *		  <dt><b>POST-condition</b><dd>The new Identifier object is empty, still needs to be passed a valid identifier name
 *	  </dl>
 *	<br>
 **/

public interface IdentifierInterface extends Data<IdentifierInterface> {

  /** Initializes the Identifier object with the string as its name
   * @precondition
   *	    The given string must be a valid identifier
   * @postcondition
   *	    Creates an Identifier object with a name of String type
   **/
  public void init (String string);

  /** Returns the string representation of the identifier
    * @precondition
    *		-
    * @postcondition
    *		Returns a non-empty String
    **/
  public String toString();

  /** Returns whether or not the string representations of two identifiers are the same sequence of characters
    * @precondition
    *		-
    * @postcondition
    *		true: The identifier is equal to the source
    *   false: The identifier is not equal to the source
    **/
  public boolean equals(IdentifierInterface source);

}
