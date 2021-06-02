package javabasic.dynamic.generics.construct;

/**
 * @author lee
 * @date 2020-09-17
 *
 * 无法对构造方法是否存在 在编译器进行检查
 */
public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        System.out.println("ClassAsFactory<Employee> succeed:"+fe.get());

        try {
            ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        }catch (Exception e) {
            System.out.println("ClassAsFactory<Integer> failed");
        }
    }
}

class ClassAsFactory<T>{
    T x;
    public ClassAsFactory(Class<T> kind){
        try {
            x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    T get(){
        return x;
    }
}

class Employee{
    @Override
    public String toString() {
        return "Employee";
    }
}