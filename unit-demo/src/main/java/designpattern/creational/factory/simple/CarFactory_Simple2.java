package designpattern.creational.factory.simple;

import designpattern.creational.factory.Car;
import designpattern.creational.factory.Benz;
import designpattern.creational.factory.Bmw;
import designpattern.creational.factory.Lamborghini;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lee
 * @since 2022/10/3
 */
public class CarFactory_Simple2 {
    private static final Map<String, Car> cachedCars = new HashMap<>();

    static {
        cachedCars.put("benz", new Benz());
        cachedCars.put("bmw", new Bmw());
        cachedCars.put("lamborghini", new Lamborghini());
    }

    public Car createCar(String type) throws InstantiationException {
        Car car = cachedCars.get(type);
        if (Objects.nonNull(car)) {
            return car;
        }
        throw new InstantiationException();
    }
}
