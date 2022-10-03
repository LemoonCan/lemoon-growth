package designpattern.creational.factory.abstractfactory;

import designpattern.creational.factory.Car;
import designpattern.creational.factory.CarEle;

/**
 * @author lee
 * @date 2022/10/3
 */
public interface ICarFactory {
    Car createOilCar();
    CarEle createElectricCar();
}
