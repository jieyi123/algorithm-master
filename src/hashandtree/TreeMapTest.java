package hashandtree;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @Author pjieyi
 * @Description  有序表
 */
public class TreeMapTest {


    public static class Node{
        String name;
        Integer id;
        public Node(){}
        public Node(int i,String n){
            name=n;
            id=i;
        }
    }
    public static void main(String[] args) {
        TreeMap<Integer,String> map=new TreeMap<>();
        map.put(2,"222");
        map.put(5,"555");
        map.put(4,"444");
        map.put(6,"666");
        Integer i1=new Integer(2222);
        Integer i2=new Integer(2222);
        System.out.println(i1==i2); //false
        map.put(i1,"22222");
        System.out.println(map.get(i2));//22222
        System.out.println(map.firstKey()); //返回最小key  2
        System.out.println(map.lastKey()); //返回最大key 2222
        System.out.println(map.floorKey(3)); //返回<=3 距离3最近的key
        System.out.println(map.ceilingKey(3)); //返回>=3 距离3最近的key

        TreeMap<Node,String> nodeMap=new TreeMap<>();
        nodeMap.put(new Node(1,"zs"),"11"); //没有提供比较器会出现错误
        nodeMap.put(new Node(2,"ls"),"22");
    }
}
