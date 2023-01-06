import java.util.*;

public class MyOpenAddressingHashMap<K,V> {
    private int size;
    private final static int INIT_CAP = 4;
    private final static double LOAD_FACTOR = 0.75;
    private kvNode<K,V>[] table;

    public MyOpenAddressingHashMap(int cap){
        table = new kvNode[cap];
        size = 0;
    }

    public MyOpenAddressingHashMap(){
        this(INIT_CAP);
    }


    public class kvNode<K,V> implements Map.Entry<K,V>{
        private K key;
        private V value;
        public kvNode(K k,V v){
            this.key = k;
            this.value = v;
        }
        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }
        public V setValue(V newVal) {
            V oldVal = value;
            value = newVal;
            return oldVal;
        }
    }

    public V put(K key, V val){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(size >= table.length*LOAD_FACTOR){
            resize(2* table.length);
        }

        int i = getNodeIndex(key);
        if(i != -1){
            V oldVal = table[i].getValue();
            table[i].setValue(val);
            return oldVal;
        }

        i = hash(key);
        while(table[i] != null){
            i = (i+1) % table.length;
        }
        table[i] = new kvNode<>(key,val);
        size++;
        return null;
    }

    public V remove(K key){
        int i = getNodeIndex(key);
        if(i == -1){
            throw new NoSuchElementException();
        }

        if(size < table.length/8){
            resize(table.length/2);
        }

        V deleteVal = table[i].getValue();
        table[i] = null;
        size--;
        i = (i+1)% table.length;
        while(table[i] != null){
            kvNode<K,V> current = table[i];
            table[i] = null;
            put(current.getKey(),current.getValue());
            size--;
            i = (i+1)% table.length;
        }

        return deleteVal;
    }

    public V get(K key){
        int i = getNodeIndex(key);
        if(i == -1){
            throw new NoSuchElementException();
        }
        return table[i].getValue();
    }



    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        return getNodeIndex(key) != -1;

    }

    public int getNodeIndex(K key){
        for(int i = hash(key); table[i] != null; i = (i + 1) % table.length){
            if(table[i].getKey().equals(key)){
                return i;
            }
        }
        return -1;
    }
    public List<K> keys(){
        LinkedList<K> entryList = new LinkedList<>();
        for(kvNode<K,V> entry : table){
            if(entry != null){
                entryList.add(entry.getKey());
            }
        }
        return entryList;
    }


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    private void resize(int newCap){
        MyOpenAddressingHashMap<K,V> newMap = new MyOpenAddressingHashMap<>(newCap);
        for(kvNode<K,V> node : table){
            if(node != null){
                newMap.put(node.getKey(), node.getValue());
            }
        }
        this.table = newMap.table;
    }

//    public static void main(String[] args) {
//        MyOpenAddressingHashMap<Integer,Integer> hm = new MyOpenAddressingHashMap();
//        System.out.println(hm.isEmpty());
//        hm.put(1,100);
//        hm.put(2,200);
//        hm.put(3,300);
//        hm.remove(2);
//        System.out.println(hm.get(2));
//    }

}
