package list;

//单链表的反转
public class ReverseList {
    public  static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value=data;
        }
    }


    /**
     * 反转链表
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
