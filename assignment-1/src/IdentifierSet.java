package assignment1;

interface Set {
  /**
   * Set should not be empty
   *
   * Returns a new identifier for every iteration until the complete set has been returned, using identifierCounter
   */
  public Identifier Get();
  /**
   * Takes as argument a valid identifier that meets the idintifier conditions
   *
   * Checks if the item to add is not a duplicate of items that are already in set
   * If not, stores the identifier in the set
   */
  public void AddIdentifier(Identifier identifier);
  /**
   * Calling object and argument must be a valid set
   * Returns a new IdentifierSet containing the elements in the 1st but not in the 2nd set
   */
  public Set Intersection(Set set);


  /**
   * Calling object and argument must be a valid set
   * Returns a new IdentifierSet containing the intersecting identifiers of the calling object and the argument of the method
   * Omits duplicates
   */
  public Set Union(Set set);
  /**
   * Calling object and argument must be a valid set
   * Returns a new IdentifierSet containing all elements that are not in both sets
   */
  public Set SymmetricDifference(Set set);
  /**
   * Calling object and argument must be a valid set
   * Returns a new IdentifierSet containing the elements in the 1st but not in the 2nd set
   */
  public Set Difference(Set set);
}
