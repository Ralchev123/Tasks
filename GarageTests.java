package garage;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GarageTests {

    @Test
    public void constructor_ShouldSetSuccessfullyValues() {
        Car car = new Car("Toyota", 200, 25000);

        String expectedBrand = "Toyota";
        int expectedMaxSpeed = 200;
        double expectedPrice = 25000;

        String actualBrand = car.getBrand();
        int actualMaxSpeed = car.getMaxSpeed();
        double actualPrice = car.getPrice();

        Assert.assertEquals(expectedBrand, actualBrand);
        Assert.assertEquals(expectedMaxSpeed, actualMaxSpeed);
        Assert.assertEquals(expectedPrice, actualPrice, 0.01); // Use delta for double comparison
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ShouldThrowIllegalArgumentExceptionForInvalidMaxSpeed() {
        new Car("Honda", -180, 20000);
    }

    @Test
    public void addCar_ShouldAddSuccessfully() {
        Garage garage = new Garage();
        Car car = new Car("Toyota", 200, 25000);

        garage.addCar(car);

        List<Car> cars = garage.getCars();
        Assert.assertEquals(1, cars.size());
        Assert.assertEquals(car, cars.get(0));
    }

    @Test
    public void getCount_ShouldReturnCorrectCount() {
        Garage garage = new Garage();
        Car car1 = new Car("Honda", 180, 20000);
        Car car2 = new Car("Ford", 220, 30000);

        garage.addCar(car1);
        garage.addCar(car2);

        int count = garage.getCount();
        Assert.assertEquals(2, count);
    }

    @Test
    public void findAllCarsWithMaxSpeedAbove_ShouldReturnCorrectCars() {
        Garage garage = new Garage();
        Car car1 = new Car("Toyota", 200, 25000);
        Car car2 = new Car("Honda", 180, 20000);
        Car car3 = new Car("Ford", 220, 30000);

        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        List<Car> carsAbove200Speed = garage.findAllCarsWithMaxSpeedAbove(200);
        Assert.assertEquals(1, carsAbove200Speed.size());
        Assert.assertEquals(car3, carsAbove200Speed.get(0));
    }

    @Test
    public void getTheMostExpensiveCar_ShouldReturnCorrectCar() {
        Garage garage = new Garage();
        Car car1 = new Car("Toyota", 200, 25000);
        Car car2 = new Car("Honda", 180, 20000);
        Car car3 = new Car("Ford", 220, 30000);

        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(car3, mostExpensiveCar);
    }

    @Test
    public void findAllCarsByBrand_ShouldReturnCorrectCars() {
        Garage garage = new Garage();
        Car car1 = new Car("Toyota", 200, 25000);
        Car car2 = new Car("Honda", 180, 20000);
        Car car3 = new Car("Toyota", 220, 30000);

        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        List<Car> toyotaCars = garage.findAllCarsByBrand("Toyota");
        Assert.assertEquals(2, toyotaCars.size());
        Assert.assertTrue(toyotaCars.contains(car1));
        Assert.assertTrue(toyotaCars.contains(car3));
    }
}
