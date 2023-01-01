public class MyHashSet<K> {

    MyOpenAddressingHashMap<K,Object> map = new MyOpenAddressingHashMap<>();
    private final static Object PRESENT = new Object();

    public boolean add(K key){
        if(map.containsKey(key)){
            return false;
        }
        map.put(key,PRESENT);
        return true;
    }

    public boolean remove(K key){
        if(!map.containsKey(key)){
            return false;
        }
        map.remove(key);
        return true;
    }

    public boolean contains(K key){
        return map.containsKey(key);
    }

    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }
}
