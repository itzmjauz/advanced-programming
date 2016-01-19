package assignment2;

class Node<E extends Data> implements Clonable {
  E data;
  Node prior;
  Node next;

  public Node(E d){
    this(d, null, null);
  }
  
  public Node(E data, Node<E> prior, Node<E> next) {
    this.data = data == null ? null : data;
    this.prior = prior;
    this.next = next;
  }

  public Node<E> clone() {
    Node<E> newNode;
    
    try {
      newNode = (Node<E>) super.clone();
      newNode.data = data == null ? null : (E) data.clone();
    } catch (CloneNotSupportedException E) {
      throw new Error("Clone is not supported for Node");
    }

    return newNode;
  }
}
