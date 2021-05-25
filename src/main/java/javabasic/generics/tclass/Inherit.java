package javabasic.generics.tclass;

import java.lang.reflect.ParameterizedType;

/**
 * @author lee
 * @date 5/24/21
 * 当父类为泛型类时，子类可以为泛型类，也可以不是；当为泛型类时，泛型需满足父类的泛型边界
 */
public class Inherit extends Basic{
    public static void main(String[] args) {
        ExtensionWithSuperNoGen withSuperNoGen = new ExtensionWithSuperNoGen();
        withSuperNoGen.printGenericTypes();

        ExtensionWithSuperGen withSuperGen = new ExtensionWithSuperGen();
        withSuperGen.printGenericTypes();

        ExtensionWithGen withGen = new ExtensionWithGen();
        withGen.printGenericTypes();

        ExtensionInherit inherit = new ExtensionInherit();
        inherit.printGenericTypes();
    }

}

class ExtensionWithSuperNoGen extends Basic{
    public void printGenericTypes(){
        System.out.println("SuperNoGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }
}

class ExtensionWithSuperGen extends Basic<String>{
    public void printGenericTypes(){
        System.out.println("SuperWithGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }
}

class ExtensionWithGen<T extends String> extends Basic<String>{
    public void printGenericTypes(){
        System.out.println("WithGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }
}

class ExtensionInherit extends ExtensionWithGen<String>{
    @Override
    public void printGenericTypes(){
        System.out.println("WithGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }
}