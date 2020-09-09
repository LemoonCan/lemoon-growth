package main.javabasic.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2020-09-09
 */
public class ContainerComparison {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for (int i = 0; i < 5; i++) {
            spheres[i] = new BerylliumSphere();
        }
        System.out.println(Arrays.toString(spheres));
        System.out.println(spheres[4]);

        List<BerylliumSphere> sphereList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sphereList.add(new BerylliumSphere());
        }
        System.out.println(sphereList);
        System.out.println(sphereList.get(4));

        int[] ints = {0,1,2,3,4,5};
        System.out.println(Arrays.toString(ints));
        System.out.println(ints[4]);

        List<Integer> intList = new ArrayList<>(Arrays.asList(0,1,2,3,4,5));
        intList.add(97);
        System.out.println(intList);
        System.out.println(intList.get(4));
    }
}