package hashandtree;

import java.util.HashMap;

/**
 * @Author pjieyi
 * @Description  哈希表
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<>();
        map.put("2",1);
        map.put("3",1);
        map.put("4",1);
        String s1="1";
        String s2=new String("1");
        map.put(s1,1);
        System.out.println(s1==s2);//false
        System.out.println(map.containsKey(s2)); //true
        map.put(s2,22);
        System.out.println(map.get("1"));//22
    }
}
