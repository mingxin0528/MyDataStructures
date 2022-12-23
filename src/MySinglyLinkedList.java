
import java.util.NoSuchElementException;

public class MySinglyLinkedList<E> {

    private final Node<E> head;
    private static int size;

    private static class Node<E>{
        E val;
        Node<E> next;
        private Node(E val){
            this.val = val;
        }
    }
    MySinglyLinkedList(){
        head = new Node(null);
        head.next = null;
        size = 0;
    }

    //****add****
    public void addFirst(E element){
        Node<E> newNode = new Node<>(element);
        Node<E> temp = head.next;

        head.next = newNode;
        newNode.next = temp;

        size++;
    }
    public void addLast(E element){
        Node<E> newNode = new Node<>(element);
        Node<E> temp = getNode(size-1);

        newNode.next = null;
        temp.next = newNode;

        size++;
    }
    public void add(int index, E element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> newNode = new Node<>(element);
        Node<E> tempPrev = getNode(index-1);
        Node<E> temp = tempPrev.next;

        tempPrev.next = newNode;
        newNode.next = temp;

        size++;
    }

    //****delete****
    public E removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        E deleteVal = head.next.val;
        head.next = head.next.next;

        size--;
        return deleteVal;
    }
    public E removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> temp;
        if(size == 1){
            temp = head;
        }else{
            temp = getNode(size - 2);
        }

        E deleteVal =  temp.next.val;
        temp.next = null;

        size--;
        return deleteVal;
    }
    public E remove(int index){
        checkValidIndex(index);
        if(index == 0){
            return removeFirst();
        }
        Node<E> temp = getNode(index-1);
        E deleteVal = temp.next.val;
        temp.next = temp.next.next;

        size--;
        return deleteVal;
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
        return getNode(size-1).val;
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

        p = head;
        for(int i = 0; i < index+1; i++){
                p = p.next;
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
        while(p != null){
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println("null");
    };


//    public static void main(String[] args) {
//        MySinglyLinkedList lala = new MySinglyLinkedList<>();
//        lala.addLast(1);
//        lala.display();
//        lala.addLast(2);
//        lala.display();
//        lala.addFirst(0);
//        lala.display();
//        lala.add(0,100);
//        lala.display();
//        lala.remove(2);
//        lala.display();
//        lala.removeFirst();
//        lala.removeLast();
//        lala.display();
//        lala.set(0,100);
//        System.out.println(lala.getFirst());
//        System.out.println(lala.getLast());
//        System.out.println(lala.get(0));
//    }



}
