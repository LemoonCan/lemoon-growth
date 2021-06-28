package javabasic.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lee
 * @date 6/24/21
 */
public class ArrayStreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        List<Integer> result = list.stream()
                .filter(item->item!=1)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
