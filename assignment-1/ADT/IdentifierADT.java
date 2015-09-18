package assignment1;

/** ADT for the class Identifier
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 * @elements
 *	characters
 * @structure
 *	linear since order of characters matters
 * @domain
 * at least 1 character
 * always starts with a letter
 * only alphanumeric characters
 * @constructor
 *	IdentifierADT(String name);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>The String passed as argument to the constructor should meet the following requirements:
 *		The first character is a letter, only alphanumeric characters are allowed and the string should contain a minimum of one character.
 *		<dt><b>POST-condition</b><dd>The new
 *		Identifier object contains a name String with a valid identifier name
 *	    </dl>
 *	<br>
 **/

public interface IdentifierADT {

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
    *		Returns true when both strings are the same sequence of characters
    **/
  public boolean equals(IdentifierADT identifier);

}
