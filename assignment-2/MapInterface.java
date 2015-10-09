package assignment2;

/** Interface for the class Map
*
* @author Antoni Stevenet
* @author Tim Nederveen
* @elements
*   Key-value Wrapper objects
* @structure
*	nonLinear/keyvalue storage
* @domain
*	All possible Wrapper objects with a unique key
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
public interface MapInterface <K extends Data<K>, V extends Clonable<V>> extends Clonable<MapInterface>{

	/** Adds a key-value wrapper object to the map
	 * @preconditie
	 *		the key must not already be taken?
	 * @postconditie
	 * 		the map contains the key value pair contained in a wrapper object
	 **/
	void addKVPair(K key, V value);

	/** Returns whether the map contains a wrapper object with the given key
	 * @preconditie
	 *		-
	 * @postconditie
	 * 		true: the map object contains a wrapper with given key
	 * 		false: the map object does not contain a wrapper with given key
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

	 removeForKey
}
