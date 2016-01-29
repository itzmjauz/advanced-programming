package assignment2;

public class List<E extends Data> implements ListInterface<E> {

  public Node<E> current, last, first = null;
  private int size;

  public List() {
    current = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public List<E> init() {
    current = first = last = null;
    size = 0;
    return this;
  }

  public boolean find(E d) {
    goToFirst();
    for (int x = 0 ; x < size() ; x++) {
      if (d.compareTo(current.data)== 0) return true;
      goToNext();
    }

    return false;
  }


  public List<E> insert(E d) {
    if (!find(d)) {
      Node<E> node = new Node<E>(d);

      if (!goToFirst()) {
        first = last = node;
      } else {
        if (d.compareTo(last.data) < 0) {
          node.next = first;
          first.prior = first = node;
        } else if (d.compareTo(last.data) > 0) {
          node.prior = last;
          last.next = last = node;
        } else {
          while (d.compareTo(current.next.data) > 0) {
            goToNext();
          }

          node.next = current.next;
          current.next.prior = current.next = node;
          node.prior = current;
        }
      }

      current = node;
      size++;
    }
    return this;
  }

  public E retrieve() {
    return current.clone().data;
  }

  public List<E> remove() {
    if (size() == 1) {
      last = first = current = null;
    } else if (current == first) {
      first = current = current.next;
      current.prior = null;
    } else if (current == last) {
      last = current = current.prior;
      current.next = null;
    } else {
      current.next.prior = current.prior;
      current.prior.next = current.next;
      current = current.next;
    }

    size--;
    return this;
  }

  public boolean goToFirst() {
    return (current = first) != null;
  }

  public boolean goToLast() {
    return (current = last) != null;
  }

  public boolean goToNext() {
    return (current = current.next) != null;
  }

  public boolean goToPrevious() {
    return (current = current.prior) != null;
  }

  public List<E> clone() {
    List<E> newList = new List<E>();
    newList.init();

    if(goToFirst()) {
      do {
        newList.insert((E) current.data.clone());
      } while (goToNext());
    }

    return newList;
  }

}
