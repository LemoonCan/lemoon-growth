package designpattern.creational.factory.abstractfactory;

import designpattern.creational.factory.Car;
import designpattern.creational.factory.CarEle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lee
 * @date 2022/10/3
 */
public class CarFactoryMap {
    private static final Map<String, ICarFactory> cachedCarFactorys = new HashMap<>();

    static {
        cachedCarFactorys.put("benz", new BenzCarFactory());
        cachedCarFactorys.put("bmw", new BmwCarFactory());
        cachedCarFactorys.put("lamborghini", new LamborghiniCarFactory());
    }

    public Car createOilCar(String type) throws InstantiationException {
        ICarFactory carFactory = cachedCarFactorys.get(type);
        if (Objects.nonNull(carFactory)) {
            return carFactory.createOilCar();
        }
        throw new InstantiationException();
    }

    public CarEle createElectricCar(String type) throws InstantiationException {
        ICarFactory carFactory = cachedCarFactorys.get(type);
        if (Objects.nonNull(carFactory)) {
            return carFactory.createElectricCar();
        }
        throw new InstantiationException();
    }
}
