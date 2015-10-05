package assignment2;

public class List<E extends Data<E>> extends Clonable<ListInterface<E>> implements ListInterface {

  private int size = 0;
  private Node current = null;
  private Node last = null;
  private Node first = null;

  public boolean isEmpty() {
    return size() == 0;
  }

  public ListInterface<E> init() {
    return this;
    list = new List<Node>();
    size = 0;
  }

  public int size() {
    return size;
  }

  public ListInterface<E> insert(E d) {
    Node node = new Node(d, last, null);
    last = new Node(last.data, last.prior, node);
    Node node = new Node(d, last, null);

    if(size() == 0) {
      first = node;
      last = first;
    }

    current = node;

    return this;
  }

  public E retrieve() {
    return current.data;
  }

  public ListInterFace<E> remove() {
    current.prior = new Node(current.prior.data, current.prior.prior, current.next);
    current.next = new Node(current.next.data, current.prior, current.next.next);

    if(current == last) last = current.prior;
    if(current == first) first = current.next;

    if(!isEmpty) current = null;
    if(!current.next == null) current = current.next;
    if(!current.prior == null) current = last;

    return this;
  }

  public boolean find(E d) {
    Node tmp = new Node(current.data, current.prior, current.next);

    while(!current.next != null) {
      if(current.data == d) {
        current = tmp;
        return true;
      }
      current = current.next;
    }

    while(!current.prior != null) {
      if(current.data == d) {
        current = tmp;
        return true;
      }
      current = current.prior;
    }

    current = tmp;
    return false;
  }

  public boolean goToFirst() {
    if(!isEmpty()) {
      current = first;
      return true;
    } else return false;
  }

  public boolean goToLast() {
    current = last;
    if(isEmpty) return false;
  }

  public boolean goToNext() {
    if(current == last) return false;
    if(isEmpty) return false;

    current = current.next;
    return true;
  }

  public boolean goToPrevious() {
    if(current == first) return false;
    if(isEmpty) return false;

    current = current.prior;
    return true;
  }

  public ListInterface<E> clone() {
    //todo
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
