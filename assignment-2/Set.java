package assignment2;

public class Set<E extends Data<E>> implements SetInterface<E> {

  private List<E> set;

  public Set() {
    set = new List<E>();
  }

  public Set(SetInterface<E> set2) {
    init();

    while(set2.size() > 0) {
      add(set2.get());
      set2.remove();
    }
  }

  public void init() {
    set = new List<E>();
  }

  public int size() {
    return set.size();
  }

  public E get() {
    E d = set.retrieve();
    set.goToPrevious();
    return d;
  }

  public void add(E d) {
    if(!contains(d)) {
      set.insert(d);
    }
    set.goToLast();
  }

  public void remove() {
    set.remove();
  }

  public boolean isEmpty() {
    return set.size() == 0;
  }

  public boolean contains(E d) {
    return set.find(d);
  }

  public SetInterface<E> clone() {
    return this;
  }

  public SetInterface<E> difference(SetInterface<E> set2) {
    SetInterface<E> result = new Set();

    set.goToLast();
    for(int i = 0 ; i < size() ; i++) {
      E item = set.retrieve();
      if(!set2.contains(item)) {
        result.add(item);
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

  public SetInterface<E> union(SetInterface<E> set2) {
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

  public SetInterface<E> symmetricDifference(SetInterface<E> set2) {
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
