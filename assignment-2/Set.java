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
    Set<E> copy = source.clone();
    Set<E> result = clone();

    while(!copy.isEmpty()) {
      result.set.goToFirst();
      if(result.contains(copy.get())) result.remove();
      copy.remove();
    }


    return result;
  }

  public Set<E> intersection(Set<E> source) {
    System.out.println("intersection() (*) sourcesize: " + source.size() + " this size: " + size());
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
    Set<E> copy = source.clone();
    Set<E> result = new Set<E>();

    if(set.goToFirst()) {
      do {
        result.add(set.retrieve());
      } while(set.goToNext());
    }

    copy.set.goToFirst();
    while(!copy.isEmpty()) {
      result.add(copy.get());
      copy.remove();
    }

    return result;
  }

  public Set<E> symmetricDifference(Set<E> source) {
    System.out.println("symmetricdiff (|)");
    Set<E> diff1 = difference(source);
    Set<E> diff2 = source.difference(clone());
    Set<E> result = new Set<E>();

    if(diff1.set.goToFirst()) {
      do {
        result.add(diff1.get());
      } while(diff1.set.goToNext());
    }

    if(diff2.set.goToFirst()) {
      do {
        result.add(diff2.get());
      } while(diff2.set.goToNext());
    }

    return result;
  }

  private String setToString(Set<NaturalNumber> source) {
    String string = "";

    Set<NaturalNumber> clone = source.clone();

    while(!clone.isEmpty()) {
      if(clone.size() == 1) {
        string = clone.get().number() + string;
        clone.remove();
      } else {
        string = " " + clone.get().number() + string;
        clone.remove();
      }
    }

    return "" + string;
  }
}
