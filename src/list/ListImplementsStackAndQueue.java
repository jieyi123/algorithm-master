package list;

//链表实现栈和队列
public class ListImplementsStackAndQueue {

    public static   class Node<V>{
        Node<V> next;
        V value;
        public Node(V v){
            value=v;
        }
    }

    //初始化栈
    public static class MyStack<V>{
        private Node<V> head;
        private int size;
        public MyStack(){
            head=null;
            size=0;
        }

        //入栈
        public void push(V v){
            Node<V> cur=new Node(v);
            if (head==null){
                head=cur;
            }else {
                cur.next=head;
                head=cur;
            }
            size++;
        }

        //出栈
        public V pop(){
            V ans=null;
            if (head!=null){
                ans=head.value;
                head=head.next;
                size--;
            }
            return ans;
        }
        //查看栈顶元素
        public V peek(){
            V ans=null;
            if (head!=null){
                ans= head.value;
            }
            return ans;
        }
        //栈大小
        public int size(){
            return size;
        }
        //判断是不是为空
        public boolean isEmpty(){
            return head==null;
        }
    }

    public static class MyQueue<V>{
        private Node<V> head;
        private Node<V> tail;
        private Integer size;
        public MyQueue(){
            head=null;
            tail=null;
            size=0;
        }

        //入队列
        public void offer(V v){
            Node<V> cur=new Node<>(v);
            if (head==null){
                head=cur;
                tail=cur;
            }else {
                tail.next=cur;
                tail=cur;
            }
            size++;
        }

        //出队列
        public V poll(){
            V ans=null;
            if (head!=null){
                ans= head.value;
                head=head.next;
                size--;
            }
            if (head==null){
                tail=null;
            }
            return ans;
        }
        //查看队列第一个元素
        public V peek(){
            V ans=null;
            if (head!=null){
                ans= head.value;
            }
            return ans;
        }
        public Integer size(){
            return size;
        }

        public boolean isEmpty(){
            return size==0;
        }

    }

    public static void randomStack(int len,int maxValue){
        int size=(int)(Math.random()*(len+1));

    }



    public static void main(String[] args) {
        MyQueue<Integer> queue=new MyQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.size);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue.isEmpty());
        queue.offer(1);
        System.out.println(queue.size);
    }
}
