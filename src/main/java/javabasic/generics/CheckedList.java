package javabasic.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lee
 * @date 2020-09-22
 */
public class CheckedList {
    public static void oldStyleMethod(List probablyDogs){
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogs1 = new ArrayList<>();
        oldStyleMethod(dogs1);

        List<Dog> dogs2 = Collections.checkedList(new ArrayList<>(),Dog.class);
        oldStyleMethod(dogs2);

//        List<Pet> pets = Collections.checkedList(new ArrayList<>(),Pet.class);
//        pets.addAll(new Dog());
//        pets.addAll(new Cat());
    }
}

class Cat extends Pet{
}
class Dog extends Pet{
}

class Pet{
}