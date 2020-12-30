package main.javabasic.generics.coffee;

import main.javabasic.generics.Generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author lee
 * @date 2020-09-11
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = {Latte.class,Mocha.class,Cappuccino.class,Americano.class,Breve.class};
    private static Random rand = new Random(47);
    private int size = 0;

    public CoffeeGenerator() {
    }

    public CoffeeGenerator(int sz) {
        this.size = sz;
    }

    class CoffeeIterator implements Iterator<Coffee>{
        int count = size;

        @Override
        public boolean hasNext() {
            return count>0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee)types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        for (Coffee c:new CoffeeGenerator(5)) {
            System.out.println(c);
        }

        List<Americano> americanos = new ArrayList<>();
        List<? extends Coffee> clist = americanos;
//        clist.add((Coffee)new Breve());
        Coffee c = clist.get(0);
//        clist.set(1,new Breve());
    }
}
