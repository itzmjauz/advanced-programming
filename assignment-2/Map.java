package assignment2;

public class Map <K extends Data, V extends Clonable> implements MapInterface<K, V> {

  public List<Wrapper<K,V>> keyValuePairs;

  Map() {
    keyValuePairs = new List<Wrapper<K,V>>();
  }

  Map(Map<K, V> source) {
    this.keyValuePairs = source.keyValuePairs.clone();
  }

  public void addKVPair(K key, V value) {
    if(containsKey(key)) {
      keyValuePairs.retrieve().setValue(value);
    } else {
      keyValuePairs.insert(new Wrapper<K,V>(key, value));
    }
  }

  public boolean containsKey(K key) {
    return keyValuePairs.find(new Wrapper<K, V>(key, null));
  }

  public Map<K, V> clone() {
    return new Map<K, V>(this);
  }

  public V returnValue(K key) {
    if(containsKey(key)) return keyValuePairs.retrieve().getValue();
    else return null;
  }
}
