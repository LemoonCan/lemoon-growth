package javabasic.container.collection;

import java.util.Iterator;

/**
 * @author lee
 * @since 7/1/21
 */
public class MyCollectionTest {
    public static void main(String[] args) {
        MyCollection<String> c = new MyCollection<>();
        c.add("x");
        c.add("y");
        c.add("z");

        for (String s : c) {
            //删除会出错
            if (s.equals("y")) {
                c.remove(s);
            }
            System.out.println(s);
        }
        c.add("y");
        for (String s : c) {
            System.out.println(s);
        }

        Iterator iterator= c.iterator();
        if(iterator.hasNext()){
            if("z".equals(iterator.next())) {
                iterator.remove();
            }
        }
        for (String s : c) {
            System.out.println(s);
        }
    }
}
