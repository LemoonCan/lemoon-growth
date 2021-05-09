package javabasic.container.map;

import java.util.HashMap;

/**
 * @author lee
 * @date 5/5/21
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap(9);
        for (int i = 0; i < 13; i++) {
            map.put("only"+i,"one");
        }

    }
}
