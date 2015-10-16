package assignment2;

public class Map <K extends Data<K>, V extends Clonable<V>> implements MapInterface<K, V> {
  public List<Wrapper> keyValuePairs;

  Map() {
    keyValuePairs = new List<Wrapper>();
  }

  Map(Map<K, V> source) {
    this.keyValuePairs = source.keyValuePairs;     //GET methode van maken?
  }

  public void addKVPair(K key, V value) {
    if(!containsKey(key) && key != null) {
      Wrapper newKVPair = new Wrapper(key,value);    //moet overal met <K,V> aangeduid worden?
      keyValuePairs.insert(newKVPair);
    } else {
      //keyValuePairs.current.data.setValue(value);
      // find a correct way to do this, we can't access a private class so this ain't possibe
    }
  }

  public boolean containsKey(K key) {
    Wrapper pair;
    String keyLeft = key.toString();
    String keyRight;

    if(keyValuePairs.goToFirst()) {
      do {
        keyRight = keyValuePairs.retrieve().getKey().toString();
        if(keyLeft.equals(keyRight)) return true;
      } while(keyValuePairs.goToNext());
    }

    return false;
  }

  public V returnValue (K key) {
    String keyLeft = key.toString();
    String keyRight;

    if(keyValuePairs.goToFirst()) {
      do {
        keyRight = keyValuePairs.retrieve().getKey().toString();
        if(keyLeft.equals(keyRight)) return keyValuePairs.retrieve().getValue();
      } while(keyValuePairs.goToNext());
    }

    return null;
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

    public K getKey() {
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
