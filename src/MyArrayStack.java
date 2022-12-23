public class MyArrayStack<E> {
    MyArrayList<E> data;
    public MyArrayStack(){
        data = new MyArrayList<>();
    }

    public void push(E element){
        data.addLast(element);
    }
    public E pop(){
        return data.removeLast();
    }
    public E peek(){
        return data.get(data.size()-1);
    }
//    public static void main(String[] args) {
//        MyLinkedStack<Integer> stk = new MyLinkedStack<>();
//        stk.push(1);
//        stk.push(2);
//        System.out.println(stk.peek());
//    }
}
