package designpattern.creational.factory.factorymethod;

import designpattern.creational.factory.Bmw;
import designpattern.creational.factory.Car;

/**
 * @author lee
 * @date 2022/10/3
 */
public class BmwCarFactory implements ICarFactory {
    @Override
    public Car createCar() {
        return new Bmw();
    }
}
