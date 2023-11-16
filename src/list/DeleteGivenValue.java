package list;

import java.util.ArrayList;
import java.util.List;

//203：移除链表元素
public class DeleteGivenValue {

    public static class Node{
        int value;
        Node next;
        public Node(int v){
            value=v;
        }
    }

    /**
     *
     * @param head  链表中的头节点
     * @param num 要删除链表中的数字，全部删除
     * @return  有可能删除的是头节点，所以要返回一个新的头节点
     */
    public static Node removeNode(Node head,int num){
        //来到第一个未被删除的节点
        while (head!=null){
            if (head.value!=num){
                break;
            }
            head=head.next;
        }
        //经过上一个循环后会出现的情况
        //1 链表中全的值全是num  返回null
        //2 head来到第一个未被删除的节点 head!=null
        Node pre=head;
        Node cur=head;
        while (cur!=null){
            if (cur.value==num){
                pre.next=cur.next;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }
        return head;
    }


}
