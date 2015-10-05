package assignment2;

import assignment1.IdentifierADT;
import assignment1.IdentifierSetADT;

/** Interface for the class Set
*
* @author Antoni Stevenet
* @author Tim Nederveen
* @elements
*   objects of type Data
* @structure
*	non-Linear
* @domain
*	may be empty, but the maximal amount of elements is 20 identifiers
* @constructor
*	SetInterface();
*	    <dl>
*		<dt><b>PRE-condition</b><dd> -
*		<dt><b>POST-condition</b><dd>The newly created Set object is empty
*	    </dl>`
*   SetInterface(Setinterface source);
*	    <dl>
*		<dt><b>PRE-condition</b><dd> -
*		<dt><b>POST-condition</b><dd>The content of the newly created Set object is identical to that of the source
*	    </dl>
*	<br>
**/
public interface SetInterface {
	
e extends data	
	
	  /** Initializes the Set object
	    * @precondition
	    *	    -
	    * @postcondition
	    *	    The new created Set object is empty and can contain a maximum of 20 Identifiers
	    **/
	  void init();
	
	  /** return set size
	   * @precondition
	   *  -
	   * @postcondition
	   * 	return set size
	   */
	  int size();

	  /** Returns an identifier from the set, can be iterated to return complete set
	    * @precondition
	    * 	Set should not be empty
	    * @postcondition
	    *		Returns a new identifier for every iteration until the complete set has been returned
	    */
	  IdentifierInterface getIdentifier();

	  /** Adds an identifier to the set
	    * @precondition
	    *		Set should not be full
	    * @postcondition
	    * 	Checks if the item to add is not a duplicate of items that are already in set, if not it stores the identifier in the set
	    */
	  void addIdentifier(IdentifierInterface identifier);

	  /** removes an identifier from the set
	   *  @precondition
	   *  	set can't be empty
	   *  @postcondition
	   *  	one element will be removed thus the size decreases by 1
	   */

	  void removeIdentifier();
	  /** return whether the set is empty
	    * @precondition
	    *   -
	    * @postcondition
	    *   returns true if the set is empty, and false if the set is not empty
	    *
	    **/
	  boolean isEmpty();

	  /** return whether the set is full
	    * @precondition
	    *   -
	    * @postcondition
	    *   returns true if the set is full, and false if it isn't
	    *
	    **/
	  boolean isFull();

	  /** return whether an identifier exists within the set
	   * @precondition
	   * 	-
	   * @postcondition
	   * 	return true/false depending on whether the identifier is within the set
	   */
	  boolean contains(IdentifierADT identifier);

	  /** Returns a set with all identifiers which are in the 1st but not in the 2nd set
	    * @precondition
	    * 		-
	    * @postcondition
	    * 		Returns a new Set containing the elements in the 1st but not in the 2nd set
	    */

	  SetInterface difference(SetInterface set);

	  /** Returns a set with objects that are in both sets
	    * @precondition
	    * 		-
	    * @postcondition
	    * 		Returns a new Set containing only the elements that are in both sets
	    */
	  SetInterface intersection(SetInterface set);

	  /** Return a set with all identifiers that are in both sets
	    * @precondition
	    * -
	    * @postcondition
	    *   SUCCESS: Returns a new Set containing all elements of both sets combined, omits duplicates
	    *   FAILURE: Throws an exception if number of elements in both sets combined(not taking duplicates into account) are bigger than maximum numbers of elements in set
	    */
	  SetInterface union(SetInterface set) throws Exception;

	  /** Returns a set with all identifiers that are only in one of both sets
	    * @precondition
	    *		-
	    * @postcondition
	    *		SUCCESS: Returns a new Set containing all elements that are only in one of both sets
	    *   FAILURE: Throws an exception if number of elements in resulting set exceeds maximum set size
	    */
	  SetInterface symmetricDifference(SetInterface set) throws Exception;
	
}
