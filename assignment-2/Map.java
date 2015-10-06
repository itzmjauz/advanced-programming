package assignment2;

public class MapInterface <K extends Data<K>, V extends Clonable<V>> extends Clonable<MapInterface> {

  public void addKVPair(K key, V value) {
    Wrapper entry = new Wrapper(key, value);
  }

  public boolean containsKey(K source) {
    
  }

  public V returnValue (K source) {

  }

  public void removeForKey() {

  }

  priate class Wrapper <K extends Data, V extends Clonable> implements Data {
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

    Wrapper(Wrapper<K,V> source) {
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
    
    public Wrapper<K,V> clone() {
      return new Wrapper<K,V>(this);
    }
  }
}
