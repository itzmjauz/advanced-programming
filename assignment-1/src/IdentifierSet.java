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
  	counter++;
	  	
	  if(counter == size()) counter = 0;
	  	
	  return set[counter];
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
  
  public IdentifierSetADT Difference(IdentifierSetADT secondSet) {
  	for(int x = 0 ; x < size()) {
  		
  	}
  }
  
  public IdentifierSetADT Intersection(IdentifierSetADT set);
  public IdentifierSetADT Union(IdentifierSetADT set) throws Exception;
  public IdentifierSetADT SymmetricDifference(IdentifierSetADT set) throws Exception;
  
}
