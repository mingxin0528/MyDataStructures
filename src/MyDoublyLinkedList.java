
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> implements Iterable<E>{

    final private Node<E> head, tail;
    private static int size;

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p!= tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

    private static class Node<E>{
        E val;
        Node<E> next;
        Node<E> prev;
        private Node(E val){
            this.val = val;
        }
    }
    MyDoublyLinkedList(){
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //****add****
    public void addFirst(E element){
        Node<E> newNode = new Node<>(element);
        Node<E> temp = head.next;

        head.next = newNode;
        newNode.next = temp;
        temp.prev = newNode;
        newNode.prev = head;

        size++;
    }
    public void addLast(E element){
        Node<E> newNode = new Node<>(element);
        Node<E> temp = tail.prev;

        tail.prev = newNode;
        newNode.prev = temp;
        newNode.next = tail;
        temp.next = newNode;

        size++;
    }
    public void add(int index, E element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(element);
        Node<E> temp = getNode(index);

        newNode.prev = temp.prev;
        temp.prev.next = newNode;
        newNode.next = temp;
        temp.prev = newNode;

        size++;
    }

    //****delete****
    public E removeFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }

        Node<E> temp = head.next;
        head.next = head.next.next;
        head.next.prev = head;

        temp.prev = null;
        temp.next = null;

        size--;
        return temp.val;
    }
    public E removeLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }

        Node<E> temp = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

        temp.prev = null;
        temp.next = null;

        size--;
        return temp.val;
    }
    public E remove(int index){
        checkValidIndex(index);

        Node<E> temp = getNode(index);
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        temp.prev = null;
        temp.next = null;

        size--;
        return temp.val;
    }

    //****search****
    public E getFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return head.next.val;
    }
    public E getLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }
    public E get(int index){
        checkValidIndex(index);
        return getNode(index).val;
    }

    //****update****
    public E set(int index, E element){
        checkValidIndex(index);
        Node<E> temp = getNode(index);
        E deleteVal = temp.val;
        temp.val = element;
        return deleteVal;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    private Node<E> getNode(int index){
        checkValidIndex(index);
        Node<E> p;
        if(size/2 > index){
            p = head;
            for(int i = 0; i < index+1; i++){
                p = p.next;
            }
        }else{
            p = tail;
            for(int i = 0; i < size-index;i++){
                p = p.prev;
            }
        }
        return p;

    }
    private boolean isValidIndex(int index){
        return index >= 0 && index < size;
    }
    private void checkValidIndex(int index){
        if(!isValidIndex(index)){
            throw new IndexOutOfBoundsException();
        }
    }


    public void display(){
        System.out.println("size = " + size);
        Node<E> p = head.next;
        while(p.next != null){
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println("null");
    };


    public static void main(String[] args) {
        MyDoublyLinkedList lala = new MyDoublyLinkedList<>();
        lala.addLast(1);
        lala.addLast(2);
        lala.addFirst(0);
        lala.add(0,100);
        lala.display();
        lala.remove(0);
        lala.display();
    }



}
