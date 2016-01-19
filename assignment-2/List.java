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
    return size ==0;
  }

  public List<E> init() {
    current = null;
    size = 0;
    return this;
  }

  public List<E> insert(E d) {
    Node<E> y = new Node(d);

    if(current != null) {
      Node x = current.prior;
      Node z = current;
      if(current.prior != null) {
        x.next = y;
        y.next = z;
      }
      if(current != null) {
        z.prior = y;
        y.prior = x;
      }
    }
    current = y;
    if(last == current.prior) last = current;
    if(size() == 0) first = current;
    
    size++;
    System.out.println("inserted :" + current.data);

    return this;
  }

  public E retrieve() {
    return current.data;
  }

  public List<E> remove() {
    if(current != null) {
      Node x = current.prior;
      Node y = current.next;


      if(y != null) {
        y.prior = x;
        if(current == first) first = y;
        current = y;
      }
      if(x != null) {
        x.next = y;
        if(current == last) last = x;
        current = x;
      }
      size--;
    } else {
      current = null;
    }
    
    return this;
  }

  public boolean find(E d) {
    if(goToFirst()) {
      do {
        System.out.println("find , current: " + current);
        if(current.data.equals(d)) return true;
      } while(goToNext());
    }
    return false;
  }

  public boolean goToFirst() {
    if(isEmpty()) return false;
    current = first;
    return true;
  }

  public boolean goToLast() {
    if(isEmpty()) return false;
    current = last;
    return true;
  }

  public boolean goToNext() {
    if(current.next == null) return false;
    System.out.println("current : " + current.data + " next : " + current.next.data);
    current = current.next;
    return true;
  }

  public boolean goToPrevious() {
    if(current.prior == null) return false;
    System.out.println("current : " + current.data + " previous : " + current.prior.data);
    current = current.prior;
    return true;
  }

  public List<E> clone() {
    List<E> newList = new List<E>();
    
    if(goToFirst()) {
      do {
        newList.insert((E) current.data.clone());
      } while (goToNext());
    }

    return newList;
  }

}
