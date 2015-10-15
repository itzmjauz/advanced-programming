package assignment2;

public class Set<E extends Data<E>> implements SetInterface<E> {

  private List<E> set;

  public Set() {
    init();
  }

  public Set(SetInterface<E> source) {
    init();

    while(!source.isEmpty()) {
      add(source.get());
      source.remove();
    }

    source = clone();
  }

  public void init() {
    set = new List<E>();
  }

  public int size() {
    return set.size();
  }

  public E get() {
    return set.retrieve();
  }

  public void add(E d) {
    if(!contains(d)) {
      set.insert(d);
    }
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

  public SetInterface<E> difference(SetInterface<E> source) {
    SetInterface<E> result = new Set<E>();

    if(set.goToLast()) {
      do {
        if(!source.contains(set.retrieve())) {
          result.add(set.retrieve());
        }
      } while(set.goToPrevious());
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
