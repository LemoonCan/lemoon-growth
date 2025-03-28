package javabasic.dynamic.generics.wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @since 5/25/21
 * ?、? extends、? super 可声明在形参上、变量声明上
 * 但?、? extends 推荐用于形参
 * ? super 推荐用于变量声明，也可用于形参
 * ? extends X 支持读取，并将类型上溯为X类型
 * ? super X 支持写入X及X子类数据，读取时类型认为是Object
 */
public class QuestionMark {
    //?相关限定符 不可置于返回值前，编译报错
    //static <? extends Number> void declareReturnFront(){
    //}

    static void getFirstValue(List<?> values){
        System.out.print(values.get(0)+" ");
    }

    static void getFirstNumber(List<? extends Number> values){
        System.out.print(values.get(0)+" ");
    }

    //可使用? super，但不太明白应用场景
    static void getFirstSuperNumber(List<? super Number> values){
        System.out.print(values.get(0)+" ");
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<Float> floats = new ArrayList<>();
        ints.add(1);
        strings.add("Happy");
        floats.add(1.0f);

        System.out.println("?声明打印================");
        getFirstValue(ints);
        getFirstValue(strings);
        getFirstValue(floats);

        System.out.println();
        System.out.println("? extends 声明打印=======");
        getFirstNumber(ints);
        //getFirstNumber声明形参的泛型类型为 Number 子类，故编译报错
        //getFirstNumber(strings);
        getFirstNumber(floats);

        //若在调用处使用?相关通配符，写入子类元素会报错
        List<? extends Number> numberInherits = new ArrayList<>();
        //null值不受限制
        numberInherits.add(null);
        //numberInherits.add(1.0f);
        //numberInherits.add(3);

        //*******绕过编译检查的写入，但会检查是否为Number子类*******
        System.out.println();
        System.out.println("? extends 绕过编译期检查=============");
        numberInherits = Arrays.asList(1.0f,3,4);
        for (Number x:numberInherits) {
            System.out.print(x+" ");
        }

        //混入 String类型，编译报错
        //numberInherits = Arrays.asList(1.0f,3,"happy");

        System.out.println();
        System.out.println("? super 插入Number子类数据打印============");
        List<? super Number> numberSupers = new ArrayList<>();
        numberSupers.add(1);
        numberSupers.add(1.0f);
        //编译报错
        //shops.add("haha");
        getFirstSuperNumber(numberSupers);

        System.out.println();
        System.out.println("? super 插入Shop子类数据打印============");
        List<? super ClothingShop> shops = new ArrayList<>();
        //VarietyShop不是ClothingShop子类，编译不通过
        //shops.add(new VarietyShop());
        shops.add(new ClothingShop());
        shops.add(new SportClothingShop());
        for (Object o:shops) {
            System.out.println(o);
        }
    }
}

interface Shop{
    void open();
}
class VarietyShop implements Shop {
    @Override
    public void open() {
        System.out.println("杂货铺开店");
    }
}

class ClothingShop implements Shop {
    @Override
    public void open() {
        System.out.println("服装店开店");
    }
}

class SportClothingShop extends ClothingShop {
    @Override
    public void open() {
        System.out.println("运动服装店开店");
    }
}