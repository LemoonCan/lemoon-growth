package javabasic;

/**
 * @author lee
 * @date 2021/11/11
 */
public class ObjectTest {
    public static void main(String[] args) {
        Student x = new Student("x");
        Student y = new Student("y");
        swap(x, y);
        System.out.println("x.name " + x.getName());
        System.out.println("y.name " + y.getName());
    }

    public static void swap(Student a, Student b) {
        a.setName("a");
        b.setName("b");

        Student temp = a;
        a = b;
        b = temp;
        System.out.println("a.name " + a.getName());
        System.out.println("b.name " + b.getName());
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
