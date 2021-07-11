package javabasic.dynamic.generics.tclass;

import java.lang.reflect.ParameterizedType;

/**
 * @author lee
 * @date 5/24/21
 * 当父类为泛型类时，子类及继承的父类可自由配置是否为泛型类，由类名后是否有声明决定；
 * 当继承的父类为泛型类时，泛型需满足父类的泛型边界；不为泛型类时，父类的原泛型元素类型变为Object；
 * 当子类为泛型类时，泛型无需满足父类的泛型边界，可自由定义；且父类的原泛型元素与子类泛型无关。
 *
 * 当父类不为泛型类时，子类也不能为泛型类
 */
public class Inherit extends Basic{
    public static void main(String[] args) {
        ExtensionNoGen withSuperNoGen = new ExtensionNoGen();
        System.out.println("继承的父类未指明泛型，子类未指明泛型");
        withSuperNoGen.printGenericTypes();

        System.out.println("继承的父类指明泛型，子类未指明泛型");
        ExtensionSuperGen withSuperGen = new ExtensionSuperGen();
        withSuperGen.printGenericTypes();

        System.out.println("继承的父类指明泛型，子类指明泛型");
        ExtensionAndSuperGen withGen = new ExtensionAndSuperGen();
        withGen.printGenericTypes();

        System.out.println("子类的泛型声明不受父类限制");
        ExtensionInherit inherit = new ExtensionInherit();
        inherit.printGenericTypes();
    }

}

/**
 * 继承的父类未指明泛型，子类未指明泛型
 */
class ExtensionNoGen extends Basic{
    public void printGenericTypes(){
        System.out.println("NoGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }

    /**
     * 继承的父类未指明泛型，element 类型变为 Object，且继承的父类不是泛型类
     * @return
     */
    @Override
    public Object getElement() {
        return element;
    }
}

/**
 * 继承的父类指明泛型，子类未指明泛型
 */
class ExtensionSuperGen extends Basic<String>{
    public void printGenericTypes(){
        System.out.println("SuperGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }

    @Override
    public String getElement() {
        return super.getElement();
    }
}

/**
 * 继承的父类指明泛型，子类指明泛型
 * @param <T>
 */
class ExtensionAndSuperGen<T extends String> extends Basic<String>{
    public void printGenericTypes(){
        System.out.println("ExtensionAndSuperGen: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }

    @Override
    public String getElement() {
        return super.getElement();
    }
}

/**
 * 子类的泛型声明不受父类限制
 * @param <T>
 */
class ExtensionInherit<T extends Number> extends ExtensionAndSuperGen {
    @Override
    public void printGenericTypes(){
        System.out.println("ExtensionInherit: " + (this.getClass().getGenericSuperclass() instanceof ParameterizedType));
    }

    @Override
    public String getElement() {
        return super.getElement();
    }
}

//继承的父类泛型受父类泛型声明限制
//class ExtensionSuperGenInherit extends ExtensionSuperGen<String>{
//}
//class ExtensionAndSuperGenInherit extends ExtensionAndSuperGen<Integer>{
//}

