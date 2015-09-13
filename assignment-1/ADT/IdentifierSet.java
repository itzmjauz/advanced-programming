package assignment1;

/** ADT for the class IdentifierSet
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 * @elements
 *	identifiers of the type Identifier
 * @structure
 *	non linear
 * @domain
 *	//wat moet hier?
 * @constructor
 *	IdentifierSet();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd> -
 *		<dt><b>POST-condition</b><dd>The new created
 *		IdentifierSet object is empty and can contain a maximum of 20 Identifiers
 *	    </dl>
 *	<br>
 **/

public interface IdentifierSet {

  /** Initializes the IdentifierSet object
    * @precondition
    *	    -
    * @postcondition
    *	    The new created IdentifierSet object is empty and can contain a maximum of 20 Identifiers
    **/
  public void init ();

  /** Returns an identifier from the set, can be iterated to return complete set
    * @precondition
    * 		Set should not be empty
    * @postcondition
    *		Returns a new identifier for every iteration until the complete set has been returned
    */
  public Identifier getIdentifier();

  /** Adds an identifier to the set
    * @precondition
    *		Set should not be full
    * @postcondition
    * 		Checks if the item to add is not a duplicate of items that are already in set, if not it stores the identifier in the set
    */
  public void AddIdentifier(Identifier identifier);

  /** Returns a set with all identifiers which are in the 1st but not in the 2nd set
    * @precondition
    * 		-
    * @postcondition
    * 		Returns a new IdentifierSet containing the elements in the 1st but not in the 2nd set
    */
  public IdentifierSet Difference(IdentifierSet set);

  /** Returns a set with objects that are in both sets
    * @precondition
    * 		-
    * @postcondition
    * 		Returns a new IdentifierSet containing only the elements that are in both sets
    */
  public IdentifierSet Intersection(IdentifierSet set);

  /** Return a set with all identifiers that are in both sets
    * @precondition
    * -
    * @postcondition
    *		Throws an exception if number of elements in both sets combined(not taking duplicates into account) are bigger than maximum numbers of elements in set
    * 		Returns a new IdentifierSet containing all elements of both sets combined, omits duplicates
    */
  public IdentifierSet Union(IdentifierSet set);

  /** Returns a set with all identifiers that are only in one of both sets
    * @precondition
    *		-
    * @postcondition
    *		Throws an exception if number of elements in resulting set exceeds maximum set size
    * 		Returns a new IdentifierSet containing all elements that are only in one of both sets
    */
  public IdentifierSet SymmetricDifference(IdentifierSet set);
}
