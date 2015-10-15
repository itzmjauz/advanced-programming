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

    if(set.goToFirst()) {
      do {
        source.add(set.retrieve());
      } while (set.goToNext());
    }
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

  public SetInterface<E> intersection(SetInterface<E> source) {
    SetInterface<E> result = new Set<E>();
    SetInterface<E> copy = new Set<E>(source);

    while(!copy.isEmpty()) {
      E d = copy.get();
      if(contains(d)) {
        result.add(d);
      }
      copy.remove();
    }

    return result;
  }

  public SetInterface<E> union(SetInterface<E> source) {
    SetInterface<E> result = new Set<E>();
    SetInterface<E> copy = new Set<E>(source);

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

  public SetInterface<E> symmetricDifference(SetInterface<E> source) {
    SetInterface<E> result = new Set();
    SetInterface<E> copy = new Set(source);

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
