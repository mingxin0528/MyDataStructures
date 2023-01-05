import java.util.NoSuchElementException;

public class MyLRU {


    class kvNode{
        public int key,val;
        public kvNode next,prev;
        public kvNode(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    class DoublyLinkedList{
        private int size;
        private kvNode head;
        private kvNode tail;

        public DoublyLinkedList(){
            head = new kvNode(0,0);
            tail = new kvNode(0,0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addLast(kvNode x){
            x.prev = tail.prev;
            x.prev.next = x;
            x.next = tail;
            tail.prev = x;
            size++;
        }

        public void remove(kvNode x){
            x.prev.next = x.next;
            x.next.prev = x.prev;
            x.next = null;
            x.prev = null;
            size--;
        }

        public kvNode removeFirst(){
            if(head.next == tail){
                throw new NoSuchElementException();
            }
            kvNode x = head.next;
            remove(x);
            return x;
        }

        public int size(){
            return size;
        }
    }



    private MyLinkedHashMap<Integer,kvNode> map;
    private DoublyLinkedList cache;
    private int cap;

    public MyLRU(int capacity){
        map = new MyLinkedHashMap<>();
        cache = new DoublyLinkedList();
        this.cap = capacity;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val){
        if(map.containsKey(key)){
            deleteKey(key);
            addRecently(key,val);
            return;
        }
        if(cache.size == cap){
            removeLeastRecently();
        }
        addRecently(key,val);
    }

    // help functions, do not call
    private void makeRecently(int key){
        kvNode cur = map.get(key);
        cache.remove(cur);
        cache.addLast(cur);
    }

    private void addRecently(int key, int val){
        kvNode newNode = new kvNode(key,val);
        cache.addLast(newNode);
        map.put(key,newNode);
    }

    private void deleteKey(int key){
        kvNode x = map.get(key);
        cache.remove(x);
        map.remove(key);
    }

    private void removeLeastRecently(){
        map.remove(cache.removeFirst().key);
    }

    public static void main(String[] args) {
        MyLRU lru = new MyLRU(3);
        lru.put(1,10);
        lru.put(2,20);
        lru.put(3,30);
        System.out.println(lru.cache.size());
        lru.put(4,40);
        System.out.println(lru.cache.size());
        System.out.println(lru.get(1));
    }

}
