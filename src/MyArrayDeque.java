import java.util.NoSuchElementException;

public class MyArrayDeque<E> {
    private int size;
    private final static int INIT_CAP = 8;
    private E[] data;
    private int first,last;

    public MyArrayDeque(){
        this(INIT_CAP);
    }

    public MyArrayDeque(int capacity){
        data = (E[]) new Object[capacity];
        first = size = last = 0;
    }

    public void addFirst(E element){
        if(size == data.length){
            resize(data.length * 2);
        }
        if(first==0){
            first = data.length-1;
        }else{
            first--;
        }
        data[first] = element;

        size++;
    }
    public void addLast(E element){
        if(size == data.length){
            resize(data.length * 2);
        }
        data[last] = element;
        if(last == data.length -1){
            last = 0;
        }else{
            last++;
        }
        size++;
    }
    public E removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(size < data.length/4){
            resize(data.length / 2);
        }

        E deleteVal = data[first];
        data[first] = null;
        if(first == data.length -1){
            first = 0;
        }else{
            first ++;
        }
        size--;
        return deleteVal;
    }
    public E removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(size < data.length/4){
            resize(data.length / 2);
        }

        if(last == 0){
            last = data.length-1;
        }else{
            last--;
        }
        E deleteVal = data[last];
        data[last] = null;

        size--;
        return deleteVal;
    }
    public E getFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return data[first];
    }
    public E getLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(last == 0){
            return data[data.length-1];
        }
        return data[last--];
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int newCap){
        E[] newData = (E[]) new Object[newCap];
        for(int i = 0; i < size; i++){
            newData[i] = data[(first + i)% data.length];
        }
        first = 0;
        last = size;
        data = newData;
    }


}
