public class MyLinkedStack<E> {
    private MySinglyLinkedList<E> data;
    public MyLinkedStack(){
        data = new MySinglyLinkedList<>();
    }

    public void push(E element){
        data.addFirst(element);
    }
    public E pop(){
        return (E) data.removeFirst();
    }
    public E peek(){
        return data.getFirst();
    }

//    public static void main(String[] args) {
//        MyLinkedStack<Integer> stk = new MyLinkedStack<>();
//        stk.push(1);
//        stk.push(2);
//        System.out.println(stk.peek());
//    }
}
