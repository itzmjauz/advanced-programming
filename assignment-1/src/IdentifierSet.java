package assignment1;

/** IdentifierSet Class for assignment 1 of Advanced Programming
 *
 * @author Antoni Stevenet
 * @author Tim Nederveen
 *
 **/
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
    removeIdentifier();
    return set[size];
  }

  public void addIdentifier(IdentifierADT identifier) {
    if(!contains(identifier)) {
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

  public boolean contains(IdentifierADT identifier) {
    boolean contains = false;

    for(int i = 0 ; i < size() ; i++ ) {
      if(set[i].equals(identifier)) {
        contains = true;
      }
    }

    return contains;
  }

  public IdentifierSetADT difference(IdentifierSetADT set2) {
    IdentifierSetADT result = new IdentifierSet();

    for(int x = 0 ; x < size() ; x++) {
      if(!set2.contains(set[x])) {
        result.addIdentifier(set[x]);
      }
    }

    return result;
  }

  public IdentifierSetADT intersection(IdentifierSetADT set2) {
    IdentifierSetADT result = new IdentifierSet();
    IdentifierSetADT copy = new IdentifierSet(set2);

    while(!copy.isEmpty()) {
      IdentifierADT identifier = copy.getIdentifier();
      if(contains(identifier)) {
        result.addIdentifier(identifier);
      }
    }

    return result;
  }

  public IdentifierSetADT union(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = new IdentifierSet();
    IdentifierSetADT copy = new IdentifierSet(set2);

    for(int x = 0 ; x < size() ; x++) {
      result.addIdentifier(set[x]);
    }

    while(!copy.isEmpty()) {
      if(result.size() == MAX_SIZE) {
        throw new Exception("Resulting array exceeds maximum size");
      }
      result.addIdentifier(copy.getIdentifier());
    }

    return result;
  }

  public IdentifierSetADT symmetricDifference(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT copy = new IdentifierSet(set2);
    IdentifierSetADT result = difference(set2);         //fills result with elements in set 1 but not in 2

    IdentifierADT identifier;

    while(!copy.isEmpty()) {                            //fills result with elements in set 2 but not in 1
      identifier = copy.getIdentifier();
      if(!contains(identifier)) {
        if(result.size() == MAX_SIZE) {
          throw new Exception("Resulting array exceeds maximum size");
        }
        result.addIdentifier(identifier);
      }
    }

    return result;
  }

}
