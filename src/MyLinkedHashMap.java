
import java.util.Map;

public class MyLinkedHashMap<K,V>{

    private class kvNode<K,V> implements Map.Entry<K,V>{
        public kvNode<K,V> next;
        public kvNode<K,V> prev;
        private K key;
        private V val;
        public kvNode(K key, V val){
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return val;
        }

        @Override
        public V setValue(V value) {
            V oldVal = val;
            val = value;
            return oldVal;
        }
    }

    private MyOpenAddressingHashMap<K,kvNode<K,V>> map;
    private MyDoublyLinkedList<K> list;

    public MyLinkedHashMap(){
        map = new MyOpenAddressingHashMap<>();
        list = new MyDoublyLinkedList<>();
    }

    public V put(K key, V val){
        if(map.containsKey(key)){
            return map.get(key).setValue(val);
        }

        list.addLast(key);
        map.put(key,new kvNode<>(key,val));
        return null;
    }

    public V remove(K key){
        list.remove(map.getNodeIndex(key));
        return map.remove(key).getValue();
    }

    public V get(K key){
        return map.get(key).getValue();
    }

    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public Iterable<K> keys(){
        return list;
    }

    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

    public static void main(String[] args) {
        MyLinkedHashMap<Integer,Integer> map = new MyLinkedHashMap<>();
        map.put(1,100);
        map.put(2,200);
        map.put(3,300);
        for(Integer x : map.keys()){
            System.out.println(x);
        }
    }
}
