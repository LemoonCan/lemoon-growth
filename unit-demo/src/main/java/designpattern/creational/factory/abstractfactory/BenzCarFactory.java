package designpattern.creational.factory.abstractfactory;

import designpattern.creational.factory.BenzEle;
import designpattern.creational.factory.Car;
import designpattern.creational.factory.Benz;
import designpattern.creational.factory.CarEle;

/**
 * @author lee
 * @date 2022/10/3
 */
public class BenzCarFactory implements ICarFactory {
    @Override
    public Car createOilCar() {
        return new Benz();
    }

    @Override
    public CarEle createElectricCar() {
        return new BenzEle();
    }
}
