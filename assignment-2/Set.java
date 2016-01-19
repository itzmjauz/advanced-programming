package assignment2;

public class Set<E extends Data > implements SetInterface<E> {

  public List<E> set;

  public Set(Set<E> source) {
    set = source.set.clone();
  }

  public Set() {
    init();
  }

  public void init() {
    set = new List<E>();
  }

  public int size() {
    return set.size();
  }

  public E get() {
    System.out.println("get () : " + set.retrieve() + size());
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

  public Set<E> clone() {
    return new Set<E>(this);
  }

  public Set<E> difference(Set<E> source) {
    Set<E> result = new Set<E>();

    if(set.goToLast()) {
      do {
        if(!source.contains(set.retrieve())) {
          result.add(set.retrieve());
        }
      } while(set.goToPrevious());
    }

    return result;
  }

  public Set<E> intersection(Set<E> source) {
    Set<E> result = new Set<E>();
    Set<E> copy = new Set<E>(source);

    while(!copy.isEmpty()) {
      E d = copy.get();
      if(contains(d)) {
        result.add(d);
      }
      copy.remove();
    }

    return result;
  }

  public Set<E> union(Set<E> source) {
    Set<E> result = new Set<E>();
    Set<E> copy = new Set<E>(source);

    if(set.goToFirst()) {
      do {
        result.add(set.retrieve());
      } while(set.goToNext());
    }


    while(!copy.isEmpty()) {
      result.add(copy.get());
      copy.remove();
    }

    return result;
  }

  public Set<E> symmetricDifference(Set<E> source) {
    Set<E> result = new Set<E>();
    Set<E> copy = new Set<E>(source);

    if(set.goToFirst()) {
      do {
        if(!source.contains(set.retrieve())) {
          result.add(set.retrieve());
        }
      } while(set.goToNext());
    }

    E d;

    while(!copy.isEmpty()) {
      d = copy.get();
      if(!contains(d)) {
        result.add(d);
      }
      copy.remove();
    }

    return result;
  }
}
