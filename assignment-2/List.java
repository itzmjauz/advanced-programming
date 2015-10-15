package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

  private Node current, last, first = null;

  public List() {
    current = null;
  }

  public int size() {
    if(isEmpty()) return 0;

    Node tmp = current;
    int size = 1;
    goToFirst();
    while(tmp.next != null) {
      size += 1;
      tmp = tmp.next;
    }

    System.out.println(size);
    return size;
  }

  public boolean isEmpty() {
    return current == null;
  }

  public ListInterface<E> init() {
    current = null;
    return this;
  }

  public ListInterface<E> insert(E d) {
    Node y = new Node(d);

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
    System.out.println("current :" + current.data);

    return this;
  }

  public E retrieve() {
    return current.data;
  }

  public ListInterface<E> remove() {
    if(current != null) {
      Node x = current.prior;
      Node y = current.next;

      if(y != null) {
        y.prior = x;
        current = y;
      }
      if(x != null) {
        x.next = y;
        current = x;
      }
    } else {
      current = null;
    }

    return this;
  }

  public boolean find(E d) {
    if(goToFirst()) {
      do {
        System.out.println("find , current: " + current);
        //if(current.data == d) return true;
      } while(goToNext());
    }
    return false;
  }

  public boolean goToFirst() {
    if(isEmpty()) return false;
    while(goToPrevious()) {
      //tada
    }

    return true;
  }

  public boolean goToLast() {
    if(isEmpty()) return false;
    while(goToNext()) {
      //ezpz
    }

    return true;
  }

  public boolean goToNext() {
    if(current.next == null) return false;
    System.out.println("current : " + current.data + " next : " + current.prior.data);
    current = current.next;
    return true;
  }

  public boolean goToPrevious() {
    if(current.prior == null) return false;
    System.out.println("current : " + current.data + " previous : " + current.prior.data);
    current = current.prior;
    return true;
  }

  public ListInterface<E> clone() {
    //todo
    return new List();
  }

  // Inner class for the implementation of the List class.

  private class Node {

      E data;
      public Node prior,
           next;

      public Node(E d) {
          this(d, null, null);
      }

      public Node(E data, Node prior, Node next) {
          this.data = data == null ? null : data;
          this.prior = prior;
          this.next = next;
      }

  }

}
