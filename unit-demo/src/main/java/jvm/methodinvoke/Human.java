package jvm.methodinvoke;

/**
 * @author lee
 * @date 2022/7/21
 */
public class Human {
    public void sayHello(){
    }
}

class Man extends Human{
    public void sayHello(){
        System.out.println("Hello man~");
    }
}
class Woman extends Human{
    public void sayHello(){
        System.out.println("Hello Woman~");
    }
}

