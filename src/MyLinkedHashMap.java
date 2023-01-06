
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class MyLinkedHashMap<K,V>{

    class kvNode<K,V> implements Map.Entry<K,V>{
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
    private void addLastNode(kvNode<K,V> x){
        x.prev= tail.prev;
        tail.prev.next = x;
        x.next = tail;
        tail.prev = x;
    }

    private void removeNode(kvNode<K,V> x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        x.next = x.prev = null;
    }

    private final kvNode<K,V> head,tail;
    private MyOpenAddressingHashMap<K,kvNode<K,V>> map;

    public MyLinkedHashMap(){
        map = new MyOpenAddressingHashMap<>();
        head = new kvNode<>(null,null);
        tail = new kvNode<>(null,null);
        head.next = tail;
        tail.prev = head;

    }

    public V put(K key, V val){
        if(!map.containsKey(key)){
            kvNode<K,V> newNode = new kvNode<>(key,val);
            map.put(key,newNode);
            addLastNode(newNode);
            return null;
        }

        kvNode<K,V> node = map.get(key);
        V oldVal = node.getValue();
        node.val = val;
        return oldVal;
    }

    public V remove(K key){
        if(!map.containsKey(key)){
            throw new NoSuchElementException();
        }

        kvNode<K,V> deleteNode = map.get(key);
        removeNode(deleteNode);
        map.remove(key);
        return deleteNode.val;
    }

    public V get(K key){
        if(!map.containsKey(key)){
            return null;
        }
        return map.get(key).val;
    }

    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public Iterable<K> keys() {
        LinkedList<K> keyList = new LinkedList<>();
        for(kvNode<K,V> p =head;p!= tail;p = p.next){
            keyList.add(p.key);
        }
        return keyList;
    }

    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

//    public static void main(String[] args) {
//        MyLinkedHashMap<Integer,Integer> map = new MyLinkedHashMap<>();
//        map.put(1,100);
//        map.put(2,200);
//        map.put(3,300);
//        map.remove(1);
//        System.out.println(map.get(1));
//    }
}
