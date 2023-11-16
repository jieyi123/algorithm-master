package array;

//数组实现栈和队列
public class ArrayImplementsStackAndQueue {
    public static  class MyStack{

        private int[] arr;
        private int index;
        public MyStack(int capacity){
            arr=new int[capacity];
            index=0;
        }

        //入栈
        public void push(int value){
            if (index==arr.length){
                throw new RuntimeException("队列已满");
            }
            arr[index]=value;
            index++;
        }

        //出栈
        public int pop(){
            if (index==0){
                throw new RuntimeException("队列为空");
            }
            index--;
            return arr[index];
        }

        //查看栈
        public int peek(){
            if (index==0){
                throw new RuntimeException("队列为空");
            }
            return arr[index-1];
        }

        public int size(){
            return index;
        }
        public boolean isEmpty(){
            return index==0 ? true : false;
        }
    }

    public static class MyQueue{
        private int[] arr;
        private int head;
        private int tail;
        private int size;

        public MyQueue(int capacity){
            arr=new int[capacity];
            head=0;
            tail=0;
            size=0;
        }

        //入队列
        public void offer(int value){
            if (size== arr.length){
                throw new RuntimeException("队列已满");
            }
            arr[tail]=value;
            tail=nexIndex(tail);
            size++;
        }
        //出队列
        public int poll(){
            if (size==0){
                throw new RuntimeException("队列为空");
            }
            size--;
            int ans=arr[head];
            head=nexIndex(head);
            return ans;
        }

        //查看队头元素
        public int peek(){
            if (size==0){
                throw new RuntimeException("队列为空");
            }
            return arr[head];
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size==0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nexIndex(int i){
            return i< arr.length-1 ? i+1 : 0;
        }
    }


    public static void main(String[] args) {
        MyStack stack=new MyStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        //MyQueue queue=new MyQueue(3);
        //queue.offer(1);
        //queue.offer(2);
        //queue.offer(3);
        //System.out.println(queue.size);
        //System.out.println(queue.poll());
        //System.out.println(queue.poll());
        //System.out.println(queue.size);
        //System.out.println(queue.isEmpty());
        //System.out.println(queue.peek());
        //queue.poll();
        //System.out.println(queue.isEmpty());
        //queue.offer(1);
        //System.out.println(queue.size);
    }
}
