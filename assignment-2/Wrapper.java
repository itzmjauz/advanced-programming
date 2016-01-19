package assignment2;

public class Wrapper<K extends Data, V extends Clonable> implements Data {
  K key;
  V value;

  public Wrapper() {
    key = null;
    value = null;
  }

  public void init(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public Wrapper(K key, V value) {
    init(key, value);
  }

  public Wrapper(Wrapper<K, V> source) {
    this.key = source.getKey();
    this.value = source.getValue();
  }

  public void setKey(K key) {
    this.key = key;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void SetValue(V value) {
    this.value = value;
  }

  public boolean equals(V value) {
    return this.value == value;
  }

  public int compareTo(Object source) {
    Wrapper<K, V> source2 = (Wrapper<K, V>) source;
    return getKey().compareTo(source2.getKey());
  }

  public Wrapper<K, V> clone() {
    return new Wrapper(this);
  }
}
