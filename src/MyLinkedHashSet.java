import java.util.Iterator;

public class MyLinkedHashSet<K> {
    private MyLinkedHashMap<K,Object> map = new MyLinkedHashMap<>();
    private final static Object PRESENT = new Object();

    public boolean add(K key){
        if(containsKey(key)){
            return false;
        }
        map.put(key,PRESENT);
        return true;
    }
    public boolean remove(K key){
        if(!containsKey(key)){
            return false;
        }
        map.remove(key);
        return true;
    }
    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public Iterator<K> keys(){
        return map.keys().iterator();
    }

    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return size()==0;
    }

//    public static void main(String[] args) {
//        MyLinkedHashSet<Integer> lala = new MyLinkedHashSet<>();
//        lala.add(1);
//        lala.add(2);
//        lala.add(3);
//        System.out.println(lala.remove(2));
//        System.out.println(lala.keys());
//    }
}
