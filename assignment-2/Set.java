package assignment2;

public class Set<E extends Data<E>> implements Clonable<SetInterface<E>>, SetInterface<E> {

  private E[] set;
  private int size = 0;
  private final int MAX_SIZE = 10000;//should be infinite

  public Set() {
    set = new E[MAX_SIZE];
  }

  public Set(SetInterface<E> set) {
    init();
    E identifier;

    while(!set.isEmpty()) {
      addIdentifier(set.getIdentifier());
      set.removeIdentifier();
    }

    for(int i = 0 ; i < size() ; i++) {
      set.addIdentifier(this.set[i]);
    }
  }

  public void init() {
    set = new E[MAX_SIZE];
  }

  public int size() {
    return size;
  }

  public E getIdentifier() {
    return set[size - 1];
  }

  public void addIdentifier(E identifier) {
    if(!contains(identifier)) {
      set[size] = identifier;
      size++;
    }
  }

  public void removeIdentifier() {
    size--;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(E identifier) {
    boolean contains = false;

    for(int i = 0 ; i < size() ; i++) {
      if(set[i].equals(identifier)) {
        contains = true;
      }
    }

    return contains;
  }

  public SetInterface<E> clone() {
    return this;
  }

  public SetInterface<E> difference(SetInterface<E> set2) {
    SetInterface<E> result = new Set();

    for(int i = 0 ; i < size() ; i++) {
      if(!set2.contains(set[i])) {
        result.addIdentifier(set[i]);
      }
    }

    return result;
  }

  public SetInterface<E> intersection(SetInterface<E> set2) {
    SetInterface<E> result = new Set();
    SetInterface<E> copy = new Set(set2);

    while(!copy.isEmpty()) {
      E identifier = copy.getIdentifier();
      if(contains(identifier)) {
        result.addIdentifier(identifier);
      }
      copy.removeIdentifier();
    }

    return result;
  }

  public SetInterface<E> union(SetInterface<E> set2) throws Exception {
    SetInterface<E> result = new Set();
    SetInterface<E> copy = new Set(set2);

    for(int i = 0 ; i < size() ; i++) {
      result.addIdentifier(set[i]);
    }

    while(!copy.isEmpty()) {
      result.addIdentifier(copy.getIdentifier());
      copy.removeIdentifier();
    }

    return result;
  }

  public SetInterface<E> symmetricDifference(SetInterface<E> set2) throws Exception {
    SetInterface<E> result = new Set();
    SetInterface<E> copy = new Set(set2);

    for(int i = 0 ; i < size() ; i++) {
      if(!set2.contains(set[i])) {
        result.addIdentifier(set[i]);
      }
    }

    E identifier;

    while(!copy.isEmpty()) {
      identifier = copy.getIdentifier();
      if(!contains(identifier)) {
        result.addIdentifier(identifier);
      }
      copy.removeIdentifier();
    }

    return result;
  }


}
