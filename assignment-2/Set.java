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
    System.out.println("difference() (-)");
    Set<E> result = new Set<E>();

    if(set.goToFirst()) {
      do {
        if(!source.contains(set.retrieve())) {
          result.add(set.retrieve());
        }
      } while(set.goToNext());
    }

    return result;
  }

  public Set<E> intersection(Set<E> source) {
    System.out.println("intersection() (*)");
    Set<E> result = new Set<E>();
    Set<E> copy = source.clone();

    copy.set.goToFirst();
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
    System.out.println("union() (+)");
    Set<E> result = source.clone();

    if(set.goToFirst()) {
      do {
        result.add(set.retrieve());
      } while(set.goToNext());
    }

    return result;
  }

  public Set<E> symmetricDifference(Set<E> source) {
    System.out.println("symmetricdiff (|)");
    Set<E> result = source.clone();
    Set<E> copy = source.clone();

    if(set.goToFirst()) {
      do {
        result.add(set.retrieve());
      } while(set.goToNext());
    }

    while(!copy.isEmpty()) {
      E d = copy.get();
      if(set.find(d)) {
        result.contains(d);
        result.remove();
      }
      copy.remove();
    }

    return result;
  }
}
