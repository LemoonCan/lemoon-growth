package designpattern.creational.factory.factorymethod;

import designpattern.creational.factory.Car;
import designpattern.creational.factory.Lamborghini;

/**
 * @author lee
 * @date 2022/10/3
 */
public class LamborghiniCarFactory implements ICarFactory {
    @Override
    public Car createCar() {
        return new Lamborghini();
    }
}
