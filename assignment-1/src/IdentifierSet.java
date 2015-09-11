package assignment1;

interface Set {
  public Identifier Get();
  public void AddIdentifier(Identifier identifier);
  public Set difference(Set set);
  public Set Union(Set set);
  public Set SymmetricDifference(Set set);
  public Set Intersection(Set set);
}
