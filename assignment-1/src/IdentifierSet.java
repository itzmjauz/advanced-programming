package assignment1;

public class IdentifierSet implements IdentifierSetADT {

  private IdentifierADT[] set;
  private int size = 0;

  public IdentifierSet() {
    set = new IdentifierADT[MAX_SIZE];
  }

  public IdentifierSet(IdentifierSetADT identifierSet) {
    init();
    IdentifierADT identifier;

    while(!identifierSet.isEmpty()) {
      addIdentifier(identifierSet.getIdentifier());
      identifierSet.removeIdentifier();
    }

    for(int x = 0 ; x < size() ; x++) {
      identifierSet.addIdentifier(set[x]);
    }
}

  public void init() {
    set = new IdentifierADT[MAX_SIZE];
  }

  public int size() {
    return size;
  }

  public IdentifierADT getIdentifier() {
    return set[size - 1];
  }

  public void addIdentifier(IdentifierADT identifier) {
    if(!Contains(identifier)) {
      set[size] = identifier;
      size++;
    }
  }

  public void removeIdentifier() {
    size--;
  }

  public boolean isEmpty() {
    if(size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isFull() {
    if(size() == MAX_SIZE) {
      return true;
    } else {
      return false;
    }
  }

  public boolean Contains(IdentifierADT identifier) {
    boolean contains = false;

    for(int i = 0 ; i < size() ; i++ ) {
      if(set[i].equals(identifier)) {
        contains = true;
      }
    }

    return contains;
  }

  public IdentifierSetADT Difference(IdentifierSetADT set2) {
    IdentifierSetADT result = new IdentifierSet();

    for(int x = 0 ; x < size() ; x++) {
      if(!set2.Contains(set[x])) {
        result.addIdentifier(set[x]);
      }
    }

    return result;
  }

  public IdentifierSetADT Intersection(IdentifierSetADT set2) {
    IdentifierSetADT result = new IdentifierSet();
    IdentifierSetADT copy = new IdentifierSet(set2);

    while(!copy.isEmpty()) {
      IdentifierADT identifier = copy.getIdentifier();
      if(Contains(identifier)) {
        result.addIdentifier(identifier);
      }
      copy.removeIdentifier();
    }

    return result;
  }

  public IdentifierSetADT Union(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = new IdentifierSet();
    IdentifierSetADT copy = new IdentifierSet(set2);

    for(int x = 0 ; x < size() ; x++) {
      result.addIdentifier(set[x]);
    }

    while(!copy.isEmpty()) {
      if(result.size() == MAX_SIZE) {
        throw new Exception("Resulting array exceeds maximum size")
      }
      result.addIdentifier(copy.getIdentifier());
      copy.removeIdentifier();
    }

    return result;
  }

  public IdentifierSetADT SymmetricDifference(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = new IdentifierSet();
    IdentifierSetADT copy = new IdentifierSet(set2);

    for(int x = 0 ; x < size() ; x++) {
      if(!set2.Contains(set[x])) {
        result.addIdentifier(set[x]);
      }
    }

    IdentifierADT identifier;

    while(!copy.isEmpty()) {
      identifier = copy.getIdentifier();
      if(!Contains(identifier)) {
        if(result.size() == MAX_SIZE) {
          throw new Exception("Resulting array exceeds maximum size")
        }
        result.addIdentifier(identifier);
      }
      copy.removeIdentifier();
    }

    return result;
  }

}
