import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class Slot<K,V> {
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

    private MySinglyLinkedList<Map.Entry<K,V>> lala ;
    private int size;
    public Slot(){
        lala = new MySinglyLinkedList<>();
    }

    public V put(K key, V val){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(containsKey(key)){
            V oldVal = get(key);
            getNode(key).setValue(val);
            return oldVal;
        }
        size++;
        lala.addFirst(new kvNode<>(key,val));
        return null;
    }

    public V remove(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(!containsKey(key)){
            throw new NoSuchElementException();
        }
        size--;
        V deleteVal = getNode(key).getValue();
        lala.remove(getNodeIndex(key));

        return deleteVal;

    }


    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(!lala.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (lala.get(i).getKey() == key) {
                    return true;
                }
            }
        }
        return false;
    }

    public V get(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < size; i++) {
            if (lala.get(i).getKey() == key) {
                return lala.get(i).getValue();
            }
        }
        return null;
    }

    public LinkedList<Map.Entry<K,V>> nodes(){
        LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>();
        for(int i = 0;i<lala.size();i++){
            entryList.add(lala.get(i));
        }
        return entryList;
    }

    public int size(){
        return size;
    }

    private Map.Entry<K,V> getNode(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < size; i++){
            if(lala.get(i).getKey() == key){
                return lala.get(i);
            }
        }
        return null;
    }

    private int getNodeIndex(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < size; i++) {
            if (lala.get(i).getKey() == key) {
                return i;
            }
        }
        return -1;
    }
}
