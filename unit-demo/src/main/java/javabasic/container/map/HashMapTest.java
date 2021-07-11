package javabasic.container.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 5/5/21
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap(9);
        for (int i = 0; i < 13; i++) {
            map.put("only" + i, "one");
        }

        for (Map.Entry<String,String> item: map.entrySet()) {
            System.out.println(item.getKey()+":"+item.getValue());
        }
    }
}
