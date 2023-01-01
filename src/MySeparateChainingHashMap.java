import java.util.LinkedList;
import java.util.Map;

public class MySeparateChainingHashMap<K,V> {

    private int size;
    private Slot<K, V>[] table;
    private static final int INIT_CAP = 8;
    private static final double LOAD_FACTOR = 0.75;

    public MySeparateChainingHashMap(int cap) {
        table = new Slot[cap];
        size = 0;
        for (int i = 0; i < cap; i++) {
            table[i] = new Slot<>();
        }
    }

    public MySeparateChainingHashMap() {
        this(INIT_CAP);
    }

    public V put(K key, V value) {
        if (size >= LOAD_FACTOR * table.length) {
            resize(table.length * 2);
        }
        if (containsKey(key)) {
            V oldVal = get(key);
            table[hash(key)].put(key, value);
            return oldVal;
        }
        size++;
        table[hash(key)].put(key, value);
        return null;
    }

    public V remove(K key) {
        size--;
        return table[hash(key)].remove(key);
    }


    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Slot<K, V> slot = table[hash(key)];
        return slot.get(key);
    }


    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Slot<K, V> slot = table[hash(key)];
        return slot.containsKey(key);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    private void resize(int newCap) {
        MySeparateChainingHashMap<K,V> newMap = new MySeparateChainingHashMap<>(newCap);
        for (Slot<K,V> slot : table) {
            LinkedList<Map.Entry<K, V>> nodes = slot.nodes();
            for (Map.Entry<K, V> currentNode : nodes) {
                K key = currentNode.getKey();
                V value = currentNode.getValue();
                newMap.put(key, value);
            }
        }
        this.table = newMap.table;
    }


    public static void main(String[] args) {
        MySeparateChainingHashMap<Integer,Integer> hm = new MySeparateChainingHashMap<>(4);
        System.out.println(hm.isEmpty());
        hm.put(1,100);
        System.out.println(hm.isEmpty());
        System.out.println(hm.get(1));
        hm.put(2,200);
        System.out.println(hm.get(2));
        hm.put(3,300);
        System.out.println(hm.get(3));
        hm.put(0,0);
        System.out.println(hm.get(0));
        System.out.println(hm.table.length);
        hm.remove(3);
        System.out.println(hm.get(3));

    }
}