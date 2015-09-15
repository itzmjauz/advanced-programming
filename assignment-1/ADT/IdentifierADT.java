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
 *	Identifier(String name);
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

  /** Returns the name of the Identifier object
    * @precondition
    *		-
    * @postcondition
    *		Returns a non-empty String containing a valid Identifier name
    **/
  public String getName();

  /** Returns whether or not the name of the identifier given as argument is the same as the name of the calling identifier
    * @precondition
    *		-
    * @postcondition
    *		Returns true when both strings are equal, and false otherwise
    **/
  public boolean equals(IdentifierADT identifier);

}
