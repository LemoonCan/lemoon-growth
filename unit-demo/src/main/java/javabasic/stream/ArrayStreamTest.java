package javabasic.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lee
 * @since 6/24/21
 */
public class ArrayStreamTest {
    public static void main(String[] args) {
        testFilter();
        //默认从小到大排序
        testSort();
    }

    public static void testFilter() {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        List<Integer> result = list.stream()
                .filter(item -> item != 1)
                .collect(Collectors.toList());
        //无元素情况返回空list
        System.out.println(result);
    }

    public static void testSort() {
        List<User> users = Arrays.asList(new User(4), new User(5), new User(1));
        List<User> sortUsers = users.stream().sorted(Comparator.comparing((User user) -> user.getId()))
                .collect(Collectors.toList());
        System.out.println(users);
        System.out.println(sortUsers);
    }

}

class User {
    int id;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}
