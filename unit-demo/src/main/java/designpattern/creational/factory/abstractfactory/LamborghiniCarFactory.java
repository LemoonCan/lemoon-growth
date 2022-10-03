package designpattern.creational.factory.abstractfactory;

import designpattern.creational.factory.Car;
import designpattern.creational.factory.CarEle;
import designpattern.creational.factory.Lamborghini;
import designpattern.creational.factory.LamborghiniEle;

/**
 * @author lee
 * @date 2022/10/3
 */
public class LamborghiniCarFactory implements ICarFactory {

    @Override
    public Car createOilCar() {
        return new Lamborghini();
    }

    @Override
    public CarEle createElectricCar() {
        return new LamborghiniEle();
    }
}
