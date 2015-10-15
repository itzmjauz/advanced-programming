package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

  private Node current, last, first;

  public List() {
    current = new Node(null);
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
    Node node = new Node(d, current, current.next);
    if(current == null) current = node;
    if(current.next != null) current.next = current.next.prior = node;

    return this;
  }

  public E retrieve() {
    return current.data;
  }

  public ListInterface<E> remove() {
    if(current.next != null) current.next.prior = current.prior;
    if(current.prior != null) current.prior.next = current.next;
    if(current.next == null) current = current.prior;
    else current = current.next;

    return this;
  }

  public boolean find(E d) {
    if(goToFirst()) {
      do {
        if(current.data == d) return true;
      } while(goToNext());
    }

    return false;
  }

  public boolean goToFirst() {
    if(current == null) return false;

    while(current.prior != null) {
      current = current.prior;
    }

    return true;
  }

  public boolean goToLast() {
    if(current == null) return false;

    while(current.next != null) {
      current = current.next;
    }

    return true;
  }

  public boolean goToNext() {
    if(current.next == null) return false;
    current = current.next;
    return true;
  }

  public boolean goToPrevious() {
    if(current.prior == null) return false;
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
      Node prior,
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
