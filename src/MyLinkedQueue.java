public class MyLinkedQueue<E> {
    private MyDoublyLinkedList<E> data;
    public MyLinkedQueue(){
        data = new MyDoublyLinkedList<>();
    }

    public void offer(E element){
        data.addLast(element);
    }
    public E poll(){
        return data.removeFirst();
    }
    public E peek(){
        return data.getFirst();
    }

    public static void main(String[] args) {
        MyLinkedQueue<Integer> q = new MyLinkedQueue<>();
        q.offer(1);
        q.offer(2);
        System.out.println(q.poll());
    }
}
