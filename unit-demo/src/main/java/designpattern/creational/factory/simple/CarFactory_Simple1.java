package designpattern.creational.factory.simple;

import designpattern.creational.factory.Benz;
import designpattern.creational.factory.Bmw;
import designpattern.creational.factory.Car;
import designpattern.creational.factory.Lamborghini;

/**
 * @author lee
 * @date 2022/10/3
 */
public class CarFactory_Simple1 {
    public Car createCar(String type) throws InstantiationException {
        if ("benz".equals(type)) {
            return new Benz();
        } else if ("bmw".equals(type)) {
            return new Bmw();
        } else if ("lamborghini".equals(type)) {
            return new Lamborghini();
        }
        throw new InstantiationException();
    }
}
