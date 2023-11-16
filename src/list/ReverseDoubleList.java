package list;

import java.util.ArrayList;

//双链表的反转
public class ReverseDoubleList {
    //双向链表结构
    public static class DoubleNode{
        int value;
        DoubleNode last;
        DoubleNode next;
        public DoubleNode(int data){
            value=data;
        }
    }

    //双向链表的反转
    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode next=null;
        DoubleNode pre=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }


    public static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }

    public static DoubleNode randomDoubleList(int len,int value){
        int size=(int)(Math.random()*len+1);
        if (size==0){
            return null;
        }
        DoubleNode head=new DoubleNode((int)(Math.random()*value+1));
        DoubleNode pre=head;
        size--;
        while (size!=0){
            DoubleNode cur=new DoubleNode((int)(Math.random()*value+1));
            pre.next=cur;
            cur.last=pre;
            pre=cur;
            size--;
        }
        return head;
    }

    public static boolean checkDoubleListEqual(DoubleNode node1,DoubleNode node2){
        boolean null1 = node1 == null;
        boolean null2 = node2 == null;
        if (null1 && null2) {
            return true;
        }
        if (null1 ^ null2) {
            return false;
        }
        if (node1.last != null || node2.last != null) {
            return false;
        }
        DoubleNode end1=null;
        DoubleNode end2=null;
        while (node1!=null && node2!=null){
            if (node1.value!= node2.value){
                return false;
            }
            end1=node1;
            end2=node2;
            node1=node1.next;
            node2=node2.next;
        }
        if (node1!=null || node2!=null){
            return false;
        }
        while (end1!=null&&end2!=null){
            if (end1.value!= end2.value){
                return false;
            }
            end1=end1.last;
            end2=end2.last;
        }
        return end1==null && end2==null;
    }

    public static void main(String[] args) {
        int len=5;
        int value=100;
        int testTime=1000;
        System.out.println("测试开始");
        for (int i=0;i<testTime;i++) {
            DoubleNode testNode = randomDoubleList(len, value);
            DoubleNode node1 = reverseDoubleList(testNode);
            DoubleNode node2 = testReverseDoubleList(node1);
            if (!checkDoubleListEqual(testNode,node2)){
                System.out.println("测试出错");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
