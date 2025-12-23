package pl.edu.pw.ee.struct.map;

public interface MapInterface<V> {

    public void put(byte[] key, V value);

    public V get(byte[] key);
}
