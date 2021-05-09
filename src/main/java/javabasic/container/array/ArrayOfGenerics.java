package javabasic.container.array;

import javabasic.container.BerylliumSphere;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @date 2020-09-09
 */
public class ArrayOfGenerics {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[])la;
        ls[0]=new ArrayList<>();
//        illegal
//!        ls[1]=new ArrayList<Integer>();

        //List<String> 是 Object 的子类
        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();

        List<BerylliumSphere>[] spheres = new List[10];
        for (int i = 0; i < spheres.length; i++) {
            spheres[i] = new ArrayList<>();
        }
    }
}

class ArrayOfGenericType<T>{
    T[] array;

    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        this.array = (T[])new Object[size];
//        illegal!
//!        this.array = new T[size];
    }
}
