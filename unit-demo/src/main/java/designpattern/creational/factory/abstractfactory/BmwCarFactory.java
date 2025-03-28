package designpattern.creational.factory.abstractfactory;

import designpattern.creational.factory.BmwEle;
import designpattern.creational.factory.Car;
import designpattern.creational.factory.Bmw;
import designpattern.creational.factory.CarEle;

/**
 * @author lee
 * @since 2022/10/3
 */
public class BmwCarFactory implements ICarFactory {
    @Override
    public Car createOilCar() {
        return new Bmw();
    }

    @Override
    public CarEle createElectricCar() {
        return new BmwEle();
    }
}
