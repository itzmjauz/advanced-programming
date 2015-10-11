package assignment2;

public class Map <K extends Data<K>, V extends Clonable<V>> extends Clonable<MapInterface> implements MapInterface {
  private List<Wrapper<K,V>> keyValuePairs;

  Map() {
    keyValuePairs = new List<Wrapper>();
  }

  Map(MapInterface<K,V> source) {
    keyValuePairs = source.keyValuePairs      //GET methode van maken?
  }

  public void addKVPair(K key, V value) {
    if(!containsKey) {
      Wrapper newKVPair = new Wrapper(key,value)    //moet overal met <K,V> aangeduid worden?
      keyValuePairs.insert(newKVPair);
    } else {
      keyValuePairs.current.data.setValue(value);   //containsKey guarantees that current is the node with same key
    }
  }

  public boolean containsKey(K key) {
    return keyValuePairs.find(new Wrapper<K,V>(key, null);
  }

  public V returnValue (K key) {
    if(containsKey(key) {
      return keyValuePairs.retrieve();    //correct?
    } else {
      return null;
    }
  }

  public void removeForKey() {        //is this necessary?

  }

  private class Wrapper implements Data<Wrapper> {
    K key;
    V value;

    Wrapper() {
      key = null;
      value = null;
    }

    private void init(K key, V Value) {
      this.key = key;
      this.value = value;
    }

    Wrapper(K key, V value) {
      init(key, value);
    }

    Wrapper(Wrapper source) {
      this.key = source.getKey();
      this.value = source.getValue();
    }

    void setKey(K key) {
      this.key = key;
    }

    K getKey() {
      return key;
    }

    void setValue(V value) {
      this.value = value;
    }

    void getValue() {
      return value;
    }

    boolean equals(V value) {
      return this.value == value; // or use .equals method ?
    }

    int compareTo(Object source) {        //should this be another type than object?
      if (source instanceof Wrapper) {
        return key.compareTo(source.getKey());
      }
      throw new Error ("Object not a finite list")    //necessary?
    }

    public Wrapper<K,V> clone() {
      return new Wrapper<K,V>(this);
    }
  }
}
