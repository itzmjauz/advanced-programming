package assignment2;

public class Set<E extends Data<E>> implements SetInterface<E> {

  private List<E> set;

  public Set(SetInterface<E> source) {
    init();

    source = (Set<E>) source;
    while(source.size() > 0) {
      add(source.get());
      source.remove();
    }

    if(set.goToFirst()) {
      do {
        source.add(set.retrieve());
      } while (set.goToNext());
    }
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

  public SetInterface<E> clone() {
    SetInterface<E> newSet = new Set<E>();
    SetInterface<E> tempSet = new Set<E>();

    while(!isEmpty()) {
      E d = get();
      remove();
      newSet.add(d);
      tempSet.add(d);
    }

    while(!tempSet.isEmpty()) {
      E d = tempSet.get();
      tempSet.remove();
      add(d);
    }

    return newSet;
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
    SetInterface<E> result = new Set<E>();
    SetInterface<E> copy = new Set<E>(source);

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
