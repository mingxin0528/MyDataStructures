import java.util.NoSuchElementException;

public class MyLinkedDeque<E> {
    private MyDoublyLinkedList<E> data;
    private int size;

    public MyLinkedDeque(){
        data = new MyDoublyLinkedList<>();
        size = 0;
    }

    public void addFirst(E element){
        data.addFirst(element);
        size++;
    }
    public void addLast(E element){
        data.addLast(element);
        size++;
    }
    public E removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        size--;
        return data.removeFirst();
    }
    public E removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        size--;
        return data.removeLast();
    }
    public E getFirst(){
        if(isEmpty()){
           throw new NoSuchElementException();
        }
        return data.getFirst();
    }
    public E getLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return data.getLast();
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }


}
