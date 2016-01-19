package assignment2;

/** Interface for the class Map
*
* @author Antoni Stevenet
* @author Tim Nederveen
* @elements
*       Key-Value pairs
* @structure
*	nonLinear/keyvalue storage
* @domain
*	All possible Key-Value pairs with a unique key
* @constructor
*	MapInterface();
*	    <dl>
*		<dt><b>PRE-condition</b><dd> -
*		<dt><b>POST-condition</b><dd>The content of the newly created Map object is undefined
*	    </dl>`
*   MapInterface(Mapinterface source);
*	    <dl>
*		<dt><b>PRE-condition</b><dd> -
*		<dt><b>POST-condition</b><dd>The content of the newly created Map object is identical to that of the source
*	    </dl>
*	<br>
**/
public interface MapInterface <K extends Data, V extends Clonable> extends Clonable {

	/** Adds a key-value pair to the map
	 * @preconditie
	 * 		-
	 * @postconditie
	 * 		SUCCESS: The map contains the key value pair
	 * 		FAILURE: Throws exception if key does not exist
	 **/
	void addKVPair(K key, V value);

	/** Returns whether the map contains a key-value pair with the given key
	 * @preconditie
	 *		-
	 * @postconditie
	 * 		true: the map object contains a key-value pair with given key
	 * 		false: the map object does not contain a key-value pair with given key
	 **/
	boolean containsKey(K source);

	/** Returns the value for given key if it is present
	 * @preconditie
	 *		-
	 * @postconditie
	 * 		The value returned is equal to the value stored for the given key when the key is present in the map
	 * 		if the key is not present, the value returned is null
	 **/
	 V returnValue (K source);
}
