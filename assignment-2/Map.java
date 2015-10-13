package assignment2;

public class Map <K extends Data<K>, V extends Clonable<V>> implements Clonable<MapInterface>, MapInterface<K, V> {
  public List<Wrapper> keyValuePairs;

  Map() {
    keyValuePairs = new List<Wrapper>();
  }

  Map(Map source) {
    this.keyValuePairs = source.keyValuePairs;     //GET methode van maken?
  }

  public void addKVPair(K key, V value) {
    if(!containsKey(key)) {
      Wrapper newKVPair = new Wrapper(key,value);    //moet overal met <K,V> aangeduid worden?
      keyValuePairs.insert(newKVPair);
    } else {
      //keyValuePairs.current.data.setValue(value);
      // find a correct way to do this, we can't access a private class so this ain't possibe
    }
  }

  public boolean containsKey(K key) {
    return keyValuePairs.find(new Wrapper(key, null));
  }

  public V returnValue (K key) {
    if(containsKey(key)) {
      //return keyValuePairs.retrieve();    //correct?
      return null;
    } else {
      return null;
    }
  }

  public Map<K, V> clone() {
    return this;
  }

  private class Wrapper implements Data<Wrapper>, Comparable<Wrapper> {
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

    V getValue() {
      return value;
    }

    boolean equals(V value) {
      return this.value == value; // or use .equals method ?
    }

    public int compareTo(Wrapper source) {
      return source.getKey().compareTo(getKey());
    }

    public Wrapper clone() {
      return new Wrapper(this);
    }
  }
}
