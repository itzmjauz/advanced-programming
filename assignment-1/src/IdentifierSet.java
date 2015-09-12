package assignment1;

interface Set {
  /**
   * Set should not be empty
   *
   * Returns a new identifier for every iteration until the complete set has been returned, using identifierCounter
   */
  public Identifier Get();
  public void AddIdentifier(Identifier identifier);
  public Set difference(Set set);
  public Set Union(Set set);
  public Set SymmetricDifference(Set set);
  public Set Intersection(Set set);
}
