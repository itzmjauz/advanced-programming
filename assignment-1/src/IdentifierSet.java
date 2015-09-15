package assignment1;

public class IdentifierSet implements IdentifierSetADT {

  private IdentifierADT[] set;
  private int size = 0;
  private int counter = 0;

  public IdentifierSet() {
    set = new IdentifierADT[MAX_SIZE];
  }

  public void init() {
    set = new IdentifierADT[MAX_SIZE];
  }

  public int size() {
    return size;
  }

  public IdentifierADT getIdentifier() {
    if(counter == size()) counter = 0;

    return set[counter++];
  }

  public void AddIdentifier(IdentifierADT identifier) {
    set[size] = identifier;

    size++;
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
        result.AddIdentifier(set[x]);
      }
    }

    return result;
  }

  public IdentifierSetADT Intersection(IdentifierSetADT set2) {
    IdentifierSetADT result = new IdentifierSet();

    for(int x = 0 ; x < size() ; x++) {
      if(set2.Contains(set[x])) {
        result.AddIdentifier(set[x]);
      }
    }

    return result;
  }

  public IdentifierSetADT Union(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = new IdentifierSet();

    for(int x = 0 ; x < size() ; x++) {
      if(!set2.Contains(set[x])) {
        result.AddIdentifier(set[x]);
      }
    }

    for(int x = 0 ; x < set2.size(); x++) {
      result.AddIdentifier(set2.getIdentifier());
    }

    return result;
  }

  public IdentifierSetADT SymmetricDifference(IdentifierSetADT set2) throws Exception {
    IdentifierSetADT result = new IdentifierSet();

    for(int x = 0 ; x < size() ; x++) {
      if(!set2.Contains(set[x])) {
        result.AddIdentifier(set[x]);
      }
    }

    IdentifierADT identifier;
    for(int x = 0 ; x < set2.size() ; x++) {
      identifier = set2.getIdentifier();
      if(!Contains(identifier)) {
        result.AddIdentifier(identifier);
      }
    }

    return result;
  }

}
