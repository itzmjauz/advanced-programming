package assignment1;

public class IdentifierSet {

    /**
     * Assignment 1 for course Advanced Programming
     * Students: Antoni Stevenet & Tim Nederveen
     */

    private final int SIZE = 20;
    private int identifierCounter;
    private int elementsReturned;
    private Identifier[] identifierArray;

    public IdentifierSet(){
        identifierArray = new Identifier[SIZE];
    }

    /**
     * Set should not be empty
     *
     * Returns a new identifier for every iteration until the complete set has been returned, using identifierCounter
     */
    public Identifier returnIdentifier(){

    }


    /**
     * Takes as argument a valid identifier that meets the idintifier conditions
     *
     * Checks if the item to add is not a duplicate of items that are already in set
     * If not, stores the identifier in the set
     * identifier can't be added if the set is full and there isn't already a duplicate of it within the set
     */
    public void addIdentifier(Identifier identifier){

    }


    /**
     * Calling object and argument must be a valid set
     * Returns a new IdentifierSet containing the elements in the 1st but not in the 2nd set
     */
    public IdentifierSet returnDifference(IdentifierSet set) {

    }


    /**
     * Calling object and argument must be a valid set
     * Returns a new IdentifierSet containing the intersecting identifiers of the calling object and the argument of the method
     */
    public IdentifierSet returnIntersection(IdentifierSet set) {

    }


    /**
     * Calling object and argument must be a valid set
     * Returns a new IdentifierSet containing the intersecting identifiers of the calling object and the argument of the method
     * Omits duplicates
     */
    public IdentifierSet returnUnion(IdentifierSet set) {
        //check for duplicates
    }


    /**
     * Calling object and argument must be a valid set
     * Returns a new IdentifierSet containing all elements that are not in both sets
     */
    public IdentifierSet returnSymDiff(IdentifierSet set) {

    }
}
