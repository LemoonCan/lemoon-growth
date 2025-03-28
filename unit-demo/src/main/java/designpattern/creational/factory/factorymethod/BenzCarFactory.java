package designpattern.creational.factory.factorymethod;

import designpattern.creational.factory.Benz;
import designpattern.creational.factory.Car;

/**
 * @author lee
 * @since 2022/10/3
 */
public class BenzCarFactory implements ICarFactory {
    @Override
    public Car createCar() {
        return new Benz();
    }
}
