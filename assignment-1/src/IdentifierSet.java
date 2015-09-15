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

  public IdentifierSetADT Difference(IdentifierSetADT set2) {
    IdentifierADT identifier1, identifier2;
    IdentifierSetADT result = new IdentifierSet();
    boolean contains;

    for(int x = 0 ; x < size() ; x++) {
      identifier1 = set[x];
      contains = false;

      for(int y = 0 ; y < set2.size() ; y++) {
        identifier2 = set2.getIdentifier();

        if(identifier1.equals(identifier2)) {
          contains = true;
        }
      }

      if(!contains) {
        result.AddIdentifier(identifier1);
      }
    }

    return result;
  }

  public IdentifierSetADT Intersection(IdentifierSetADT set2) {
    IdentifierADT identifier1, identifier2;
    IdentifierSetADT result = new IdentifierSet();
    boolean contains;

    for(int x = 0 ; x < size() ; x++) {
      identifier1 = set[x];
      contains = false;

      for(int y = 0 ; y < set2.size() ; y++) {
        identifier2 = set2.getIdentifier();

        if(identifier1.equals(identifier2)) {
          contains = true;
        }
      }

      if(contains) {
        result.AddIdentifier(identifier1);
      }
    }

    return result;
  }

  public IdentifierSetADT Union(IdentifierSetADT set2) throws Exception {
    IdentifierADT identifier1, identifier2;
    IdentifierSetADT result = new IdentifierSet();

    for(int x = 0 ; x < size() ; x++) {
      identifier1 = set[x];

      for(int y = 0 ; y < set2.size() ; y++) {
        identifier2 = set2.getIdentifier();

      }
    }

    return result;
  }

  public IdentifierSetADT SymmetricDifference(IdentifierSetADT set) throws Exception;

}
