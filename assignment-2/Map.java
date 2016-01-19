package assignment2;

public class Map <K extends Data, V extends Clonable> implements MapInterface<K, V> {
  private List<Wrapper<K,V>> keyValuePairs;

  Map() {
    keyValuePairs = new List<Wrapper<K,V>>();
  }

  Map(Map<K, V> source) {
    this.keyValuePairs = source.keyValuePairs.clone();
  }

  public void add(K key, V value) {
    if(contains(key)) keyValuePairs.current.data.set(value);
    else {
      keyValuePairs.insert(new Wrapper<K,V>(key, value));
    }
  }

  public boolean contains(K key) {
    return keyValuePairs.find(new Wrapper<K, V>(key, null));
  }

  public Map<K, V> clone() {
    return new Map<K, V>(this);
  }

  public V returnValue(K key) {
    if(contains(key)) return keyValuePairs.retrieve().getValue();
    else return null;
  }
}
