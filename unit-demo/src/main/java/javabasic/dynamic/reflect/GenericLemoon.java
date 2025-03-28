package javabasic.dynamic.reflect;

import java.util.List;

/**
 * @author lee
 * @since 6/2/21
 */
public class GenericLemoon<X,Y extends Number> extends Lemoon {
    List<String> list;

    public X xy(X x,Y y) throws NoSuchFieldException{
        return x;
    }
}
