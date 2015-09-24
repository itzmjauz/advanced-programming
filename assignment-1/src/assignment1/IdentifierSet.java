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

  public void resetSize(int size){
    this.size = size;
  }

  public IdentifierADT getIdentifier() {
    removeIdentifier();
    return set[size];
  }
  
  public void removeIdentifier() {
    size--;
  }
  
  public void addIdentifier(IdentifierADT identifier) {
    if(!Contains(identifier)) {
      set[size] = identifier;
      size++;
    }
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
    int set2Size = set2.size();

    while(!set2.isEmpty()) {
      IdentifierADT identifier = set2.getIdentifier();
      if(Contains(identifier)) {
        result.addIdentifier(identifier);
      }
    }
    set2.resetSize(set2Size);
    
    return result;
  }

  public IdentifierSetADT Union(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = new IdentifierSet();
    int set2Size = set2.size();

    for(int x = 0 ; x < size() ; x++) {
      result.addIdentifier(set[x]);
    }

    while(!set2.isEmpty()) {
      if(result.size() == MAX_SIZE) {
        throw new Exception("Resulting array exceeds maximum size");
      }
      result.addIdentifier(set2.getIdentifier());
    }
    set2.resetSize(set2Size);
    return result;
  }

  public IdentifierSetADT SymmetricDifference(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = Difference(set2);
    int set2Size = set2.size();

    IdentifierADT identifier;

    while(!set2.isEmpty()) {
      identifier = set2.getIdentifier();
      if(!Contains(identifier)) {
        if(result.size() == MAX_SIZE) {
          throw new Exception("Resulting array exceeds maximum size");
        }
        result.addIdentifier(identifier);
      }
    }
    set2.resetSize(set2Size);

    return result;
  }

}
