import java.util.NoSuchElementException;

public class MyArrayList<E>{
    private static final int INIT_CAP = 10;
    private static final int RESIZE_RATIO = 2;
    private  E[] data;
    private int size = 0;
    public MyArrayList(){
        this(INIT_CAP);

    }
    public MyArrayList(int size){
        data = (E[]) new Object[size];
    }

    // ****add*****
    public void addLast(E element){
        if(size == data.length){
            resize(RESIZE_RATIO * data.length);
        }
        data[size] = element;
        size++;
    }

    public void add(int index, E element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(size == data.length){
            resize(RESIZE_RATIO * data.length);
        }

        System.arraycopy(data,index,data,index+1,size-index);
        data[index] = element;
        size++;

    }

    // ****delete*****
    public E remove(int index){
        checkValidIndex(index);
        if(size <= data.length/4){
            resize(data.length/RESIZE_RATIO);
        }
        E removedElement = data[index];
        System.arraycopy(data,index+1,data,index,size-index-1);
        size--;
        return removedElement;
    }

    public E removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(size <= data.length/4){
            resize(data.length/RESIZE_RATIO);
        }
        E removedElement = data[size-1];
        size--;
        return  removedElement;


    }


    // ****search*****
    public E get(int index){
        checkValidIndex(index);
        return data[index];
    }

    // ****update*****
    public E set(int index, E element){
        checkValidIndex(index);
        E oldVal = data[index];
        data[index] = element;
        return oldVal;
    }



    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private boolean IsValidIndex(int index){
        return index >= 0 && index < size;
    }

    private void checkValidIndex(int index){
        if(!IsValidIndex(index)){
            throw new IndexOutOfBoundsException();
        }
    }

    private void resize(int newCap){
        E[] newData = (E[]) new Object[newCap];
        System.arraycopy(data,0,newData,0,size);
        data = newData;
    }

    public void display(){
        System.out.println("size: " + size);
        for(E e : data){
            System.out.print(e + " ");
        }
    }


//    public static void main(String[] args) {
//        MyArrayList<Integer> myArr = new MyArrayList<>(4);
//        myArr.add(1);
//        myArr.add(1);
//        myArr.add(1);
//        myArr.display();
//
//        myArr.add(100);
//        myArr.display();
//        myArr.add(1999);
//        myArr.display();
//        myArr.set(0,528);
//        myArr.display();
//        myArr.remove(1);
//        myArr.display();
//
//    }

}
