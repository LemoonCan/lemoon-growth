package javabasic;

import com.alibaba.fastjson2.JSON;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lee
 * @date 6/25/21
 */
public class StringTest {
    public static void test(){
        System.out.println("test...");
    }
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("111");
        set.add("222");
        System.out.println(JSON.toJSONString(set));

        String s = "16712347777";
        StringBuffer sb = new StringBuffer(s);
        sb.replace(3, 7, "****");
        System.out.println(sb.toString());
    }
}
